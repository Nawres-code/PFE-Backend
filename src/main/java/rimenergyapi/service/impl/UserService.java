package rimenergyapi.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rimenergyapi.DataSourceBasedMultiTenantConnectionProviderImpl;
import rimenergyapi.dto.AuthorityInfoDto;
import rimenergyapi.entity.UserEntity;
import rimenergyapi.exception.model.CustomException;
import rimenergyapi.exception.model.ExceptionType;
import rimenergyapi.repository.UserEntityRepository;
import rimenergyapi.security.JwtTokenProvider;
import rimenergyapi.security.dto.Credentials;
import rimenergyapi.security.dto.PasswordConfirmation;
import rimenergyapi.security.dto.UserAddDto;
import rimenergyapi.security.dto.UserInfoDto;
import rimenergyapi.security.entity.Role;
import rimenergyapi.security.entity.User;
import rimenergyapi.security.repository.UserRepository;
import rimenergyapi.security.service.RefreshTokenService;
import rimenergyapi.service.EmailService;
import rimenergyapi.service.SmsService;
import rimenergyapi.utils.GenericUtil;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private EmailService emailService;

	@Autowired
	private SmsService smsService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	UserEntityRepository userEntityRepository;

	@Value("classpath:template/forget-credentials.html")
	private Resource forgetCredentialsTemplate;

	@Value("classpath:template/confirm-client-inscription.html")
	private Resource confirmClientInscriptionTemplate;

	@Value("classpath:template/update-password.html")
	private Resource updatePasswordTemplate;

	
    @Autowired
    private ModelMapper mapper; 
    
	@Autowired
	private DataSourceBasedMultiTenantConnectionProviderImpl dataSourceBasedMultiTenantConnectionProvider;
	
	@Autowired
	private RefreshTokenService refreshTokenService;

	public UserInfoDto signin(Credentials credential) {

		// eliminate space in login and mdp !
		credential.setPassword(credential.getPassword().replaceAll("\\s+", ""));
		credential.setLogin(credential.getLogin().replaceAll("\\s+", ""));
		
		// =======================================================================
		// =================== SUB ACCOUNTMANAGEMENT =============================
		// =======================================================================

		if (credential.getLogin().contains("_")) {
			/** GET ROOT ACCOUNT ! */
			String[] regx = credential.getLogin().split("_");
			String rootAccount = regx[0];

			final UserDetails userDetails = userDetailsService.loadUserByUsername(rootAccount);
			// load principal
			User juser = userRepository.findByUsernameOrEmail(userDetails.getUsername(), "");

			UserInfoDto SAccount = null;

			try {
				UserEntity SAccount_ = userEntityRepository.findByUsername(regx[1]);
				if (SAccount_.getPassword().equals(credential.getPassword())) {
					SAccount = new UserInfoDto();
					SAccount.setUsername(credential.getLogin());
					SAccount.setRoot(false);
					SAccount.getToken();
					SAccount.setId(juser.getId());
					SAccount.setAuthorities(new ArrayList<>());
					try {
						for (int j = 0; j < SAccount_.getAuthorities().size(); j++) {
							SAccount.getAuthorities().add(mapper.map(SAccount_.getAuthorities().get(j), AuthorityInfoDto.class) );
						}
					} catch (Exception ee) {
						ee.printStackTrace();
					}

					for (int i = 0; i < SAccount_.getInstallations().size(); i++) {
						SAccount.getInstallationIds().add(SAccount_.getInstallations().get(i).getId());
					}

					SAccount.setRoot(false);
					// User principal =
					SAccount.setToken(jwtTokenProvider.createToken(juser));
					SAccount.setRefreshToken(refreshTokenService.createRefreshToken(juser.getId()).getToken());
					this.dataSourceBasedMultiTenantConnectionProvider.addDataSource(juser.getId() + "");
					return SAccount;
				} else {
					return null;
				}

			} catch (Exception ee) {

			}
		}

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(credential.getLogin(), credential.getPassword()));

			// load user
			// User principal = userRepository.findByUsernameOrEmail(credential.getLogin(), credential.getLogin());

			final UserDetails userDetails = userDetailsService.loadUserByUsername(credential.getLogin());

			// load principal
			User juser = userRepository.findByUsernameOrEmail(userDetails.getUsername(), "");
			juser.setIdAdmin(juser.getId());
			// create token according to user
			if(juser.getRoles().contains(Role.ROLE_SUPER_ADMIN) ) {
				juser.setId(juser.getSubAccounts().get(0).getId());
			}
			
			UserInfoDto user = map2Dto(juser, jwtTokenProvider.createToken(juser));
			user.setRefreshToken(refreshTokenService.createRefreshToken(user.getId()).getToken());
			user.setSubAccounts(juser.getSubAccounts().stream().map(e-> map2Dto(e, "")).collect(Collectors.toList()));
			// save tenant in datasource db
			this.dataSourceBasedMultiTenantConnectionProvider.addDataSource(juser.getId()+"");
			return user;

		} catch (AuthenticationException e) {
			throw new CustomException(
					messageSource.getMessage("security.invalid_signin", null, LocaleContextHolder.getLocale()),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@Transactional("masterTransactionManager")
	public UserInfoDto signup(UserAddDto payload) {
		User user = modelMapper.map(payload, User.class);
		if (user.getEmail() != null && !user.getEmail().isEmpty()) {
			if (!GenericUtil.isValidEmailAddress(user.getEmail())) {
				throw new CustomException(
						messageSource.getMessage("email.validation.failed_p1", null, LocaleContextHolder.getLocale()),
						"`" + user.getEmail()
								+ messageSource.getMessage("email.validation.failed_p1", null,
										LocaleContextHolder.getLocale()),
						HttpStatus.BAD_REQUEST, ExceptionType.VALIDATION_EXCEPTION);
			}
			if (userRepository.existsByEmail(user.getEmail())) {
				throw new CustomException(messageSource.getMessage("email.validation.aleardy_in_use", null,
						LocaleContextHolder.getLocale()), HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}
		if (!userRepository.existsByUsername(user.getUsername())) {
			user.setRawPassword(user.getPassword());
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			// only user disable with role client !
			user.setEnable(false);
			user.setRoles(new ArrayList<Role>() {
				private static final long serialVersionUID = 1L;
				{
					add(Role.ROLE_CLIENT);
				}
			});
			userRepository.save(user);
			// send inscription confirmation mail !
			if (user.getEmail() != null && !user.getEmail().isEmpty()) {
				try {
					// retreive template content
					String confirmClientInscriptionTemplate = GenericUtil
							.file2String(this.confirmClientInscriptionTemplate.getFile())
							.replace("[FULLNAME]", user.getFullName());
					// replace dynamique data
					this.emailService.sendHtmlMessage(user.getEmail(), "Bienvenu à Rimenergy",
							confirmClientInscriptionTemplate);
				} catch (IOException e) {
				}
			}
			return this.modelMapper.map(user, UserInfoDto.class);
		} else {
			throw new CustomException(
					messageSource.getMessage("login.validation.aleardy_in_use", null, LocaleContextHolder.getLocale()),
					HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@Transactional("masterTransactionManager")
	public String updateUserInfo(HttpServletRequest req, UserInfoDto payload) {

		String token = jwtTokenProvider.resolveToken(req);
		Integer userId = jwtTokenProvider.getUserIdFromToken(token);

		if (payload.getId() != userId) {
			throw new AccessDeniedException("Permission denied !");
		}

		Optional<User> result = this.userRepository.findById(userId);
		User user = result.get();
		if (!result.isPresent()) {
			throw new CustomException("User with id " + userId + " was deleted");
		}

		if (payload.getEmail() != null && !payload.getEmail().isEmpty()) {

			// check format !
			if (!GenericUtil.isValidEmailAddress(payload.getEmail())) {
				throw new CustomException("Email adresse non valide",
						"`" + payload.getEmail() + "` ne respect pas la format exemple@exemple.ext !",
						HttpStatus.BAD_REQUEST, ExceptionType.VALIDATION_EXCEPTION);
			}

			// check existing !
			if (!payload.getEmail().equals(user.getEmail()) && userRepository.existsByEmail(payload.getEmail())) {
				throw new CustomException("Email is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
			}
		}

		user.setAddress(payload.getAddress());
		user.setEmail(payload.getEmail());
		user.setFixe(payload.getFixe());
		user.setPhone(payload.getPhone());
		user.setFullName(payload.getFullName());
		user.setTheme(payload.getTheme());

		this.userRepository.saveAndFlush(user);

		return jwtTokenProvider.createToken(user);
	}

	@Transactional("masterTransactionManager")
	public void updateUserPassword(HttpServletRequest req, PasswordConfirmation payload) {

		String token = jwtTokenProvider.resolveToken(req);
		Integer userId = jwtTokenProvider.getUserIdFromToken(token);

		if (payload.getId() != userId) {
			throw new AccessDeniedException("Permission denied !");
		}

		Optional<User> result = this.userRepository.findById(userId);
		User user = result.get();
		if (!result.isPresent()) {
			throw new CustomException("User with id " + userId + " was deleted");
		}

		if (!user.getRawPassword().equals(payload.getOldPassword())) {
			throw new CustomException("Old password not correct");
		}

		if (!user.getRawPassword().equals(payload.getNewPassword())) {
			user.setRawPassword(payload.getNewPassword());
			user.setPassword(passwordEncoder.encode(payload.getNewPassword()));
			this.userRepository.saveAndFlush(user);
		}

		if (payload.getReceiveNotification() != null && payload.getReceiveNotification()) {
			try {
				// retreive template content
				String updatePasswordTemplate = GenericUtil.file2String(this.updatePasswordTemplate.getFile())
						.replace("[FULLNAME]", user.getFullName()).replace("[LGOIN]", user.getUsername())
						.replace("[NEW_PASSWORD]", user.getRawPassword())
						.replace("[OLD_PASSWORD]", payload.getOldPassword());
				// replace dynamic data
				this.emailService.sendHtmlMessage(user.getEmail(), "Rimenergy password reset", updatePasswordTemplate);
			} catch (IOException e) {
			}
		}
	}

	public void forgetCredentials(String email) {
		if (!GenericUtil.isValidEmailAddress(email)) {
			throw new CustomException("Email adresse non valide",
					"`" + email + "` ne respect pas la format exemple@exemple.ext !", HttpStatus.BAD_REQUEST,
					ExceptionType.VALIDATION_EXCEPTION);
		}

		if (!userRepository.existsByEmail(email)) {
			throw new CustomException("Email adresse n'exist pas", HttpStatus.BAD_REQUEST);
		}

		User user = userRepository.findByEmail(email);

		try {
			// retreive template content
			String forgetCredentialsTemplate = GenericUtil.file2String(this.forgetCredentialsTemplate.getFile())
					.replace("[FULLNAME]", user.getFullName()).replace("[LGOIN]", user.getUsername())
					.replace("[PASSWORD]", user.getRawPassword());
			// replace dynamique data
			this.emailService.sendHtmlMessage(user.getEmail(), "Rimenergy credentials", forgetCredentialsTemplate);
		} catch (IOException e) {
		}

		if (user.getPhone() != null) {
			this.smsService.sendMessage(user.getPhone(), "Rimenergy - Informations d'authentification, Login : "
					+ user.getUsername() + ", Password : " + user.getRawPassword());
		}

	}

	public void delete(String username) {
		userRepository.deleteByUsername(username);
	}

	public UserInfoDto search(String username) {
		User user = userRepository.findEnableUser(username, username);
		if (user == null) {
			throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
		}
		return modelMapper.map(user, UserInfoDto.class);
	}

	public UserInfoDto whoami(HttpServletRequest req) {
		return modelMapper
				.map(userRepository.findEnableUser(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)),
						jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req))), UserInfoDto.class);
	}

	public Object signOut(int idTenant){
		try {
			return this.dataSourceBasedMultiTenantConnectionProvider.deleteDataSource(idTenant + "");
		} catch (Exception e) {
			throw new AccessDeniedException("access denied !");
		}
	}
	
	private  UserInfoDto map2Dto(User entity, String token ) {
		UserInfoDto userDto = new UserInfoDto();
		userDto.setId(entity.getId());
		userDto.setRoot(entity.isRoot());
		userDto.setUsername(entity.getUsername());
		userDto.setToken(token);
		userDto.setRoles(entity.getRoles());
		userDto.setEnabled(entity.isEnable());
		userDto.setAdminId(entity.getIdAdmin());
		return userDto;
	}

	public String changeSub(int subId, int id) {
		// load principal
		User juser = userRepository.findById(id);
		juser.setId(subId);
		juser.setIdAdmin(id);
		// juser.setUsername(juser.getSubAccounts().stream().filter(u->u.getId()==subId).findFirst().get().getUsername());

		// create token according to user
		String token = jwtTokenProvider.createToken(juser);
		UserInfoDto user = map2Dto(juser, token);
		user.setSubAccounts(juser.getSubAccounts().stream()
				.map(e -> map2Dto(e, ""))
				.collect(Collectors.toList())
		);
		refreshTokenService.updateUserIdByToken(token, id);
		// save tenant in datasource db
		this.dataSourceBasedMultiTenantConnectionProvider.addDataSource(juser.getId() + "");
		return token;
	}
	
	public String changeSub(int subId, int id, String refreshToken) {
		// load principal
		User juser = userRepository.findById(id);
		juser.setId(subId);
		juser.setIdAdmin(id);
		// juser.setUsername(juser.getSubAccounts().stream().filter(u->u.getId()==subId).findFirst().get().getUsername());

		// create token according to user
		String token = jwtTokenProvider.createToken(juser);
		UserInfoDto user = map2Dto(juser, token);
		user.setSubAccounts(juser.getSubAccounts().stream()
				.map(e -> map2Dto(e, ""))
				.collect(Collectors.toList())
		);
		refreshTokenService.updateUserIdByToken(refreshToken, subId);
		// save tenant in datasource db
		this.dataSourceBasedMultiTenantConnectionProvider.addDataSource(juser.getId() + "");
		return token;
	}

}
