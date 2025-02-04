package rimenergyapi.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import rimenergyapi.dto.DateIntervalDto;
import rimenergyapi.dto.accounts.ActionInfoDto;
import rimenergyapi.dto.accounts.AuthorityInfoDto;
import rimenergyapi.dto.accounts.UserInfoDto;
import rimenergyapi.entity.InstallationEntity;
import rimenergyapi.entity.UserEntity;
import rimenergyapi.repository.AccountRepository;
import rimenergyapi.repository.ActionRepository;
import rimenergyapi.repository.AuthorityRepository;
import rimenergyapi.repository.InstallationRepository;
import rimenergyapi.repository.ZonesRepository;
import rimenergyapi.security.repository.UserRepository;
import rimenergyapi.service.AccountService;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	ActionRepository actionRepository;

	@Autowired
	ZonesRepository zoneRepository;
	
	@Autowired
	InstallationRepository installationRepository;
	

	@Autowired
	AuthorityRepository authorityRepository;
	
	@Autowired 
	UserRepository userRepo;

	@Autowired
	protected ModelMapper mapper;

	@Override
	public List<UserInfoDto> getAllAccount() {
		List<UserInfoDto> accounts = new ArrayList<UserInfoDto>();
		this.accountRepository.findAll().forEach(user -> {
			UserInfoDto dto = this.mapper.map(user, UserInfoDto.class);
			for(InstallationEntity inst : user.getInstallations()) {
				dto.getInstallationsIds().add(inst.getId());
			}
			accounts.add(dto);
		});
		return accounts;
	}

	@Override
	public List<AuthorityInfoDto> getAllAuthorities() {
		List<AuthorityInfoDto> authorities = new ArrayList<AuthorityInfoDto>();
		this.authorityRepository.findAll().forEach(authority -> {
			try {
				authorities.add(this.mapper.map(authority, AuthorityInfoDto.class));
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		});
		return authorities;
	}

	@Override
	public Boolean deleteAccount(Long id) {
		try {
			if (accountRepository.existsById(id)) {
				this.accountRepository.deleteById(id);
			}
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	@Transactional
	public UserInfoDto addAccount(UserInfoDto accountDto) {
		try {
			UserEntity accountEntity = this.mapper.map(accountDto, UserEntity.class);
			// installations
			List<InstallationEntity> installations = new ArrayList<InstallationEntity>();
			accountDto.getInstallationsIds().forEach(id -> installations.add(new InstallationEntity(id)));
			accountEntity.setInstallations(installations);

			accountRepository.save(accountEntity);
			accountDto = this.mapper.map(accountEntity, UserInfoDto.class);
			for (InstallationEntity inst : accountEntity.getInstallations()) {
				accountDto.getInstallationsIds().add(inst.getId());
			}
			return accountDto;
		} catch (Exception e) {
		}
		return null;
	}

	@Override

	public UserInfoDto updateAccount(UserInfoDto accountDto) {
		try {
			if (accountRepository.existsById(accountDto.getId())) {
				UserEntity accountEntity = this.mapper.map(accountDto, UserEntity.class);
				// installations
				List<InstallationEntity> installations = new ArrayList<InstallationEntity>();
				accountDto.getInstallationsIds().forEach(id -> installations.add(new InstallationEntity(id)));
				accountEntity.setInstallations(installations);

				accountRepository.save(accountEntity);
				accountDto = this.mapper.map(accountEntity, UserInfoDto.class);
				for (InstallationEntity inst : accountEntity.getInstallations()) {
					accountDto.getInstallationsIds().add(inst.getId());
				}
				return accountDto;
			}
		} catch (Exception e) {
		}

		return null;

	}

	/* enabled or disebled acount */
	@Override
	public UserInfoDto enableOrDisableAccount(long id) {
		UserEntity account = accountRepository.getOne(id);
		account.setEnabled(!account.isEnabled());
		account = this.accountRepository.save(account);
		return this.mapper.map(account, UserInfoDto.class);
	}

	@Override
	@Transactional
	public ActionInfoDto addAction(Long id, ActionInfoDto payload) {
		/*User user = this.userRepository.findOne(id);
		if (user != null && payload != null) {
			payload.setCreatedAt(new Date());
			ActionEntity action = this.mapper.map(payload, ActionEntity.class);
			action.setUser(user);
			user.getActions().add(action);
			this.actionRepository.save(action);
			return this.mapper.map(action, ActionInfoDto.class);
		}
		return null;*/
		return null;
	}

	@Override
	public List<ActionInfoDto> getActions(Long id, DateIntervalDto dateIntervalDto) {
		/*User user = this.userRepository.findOne(id);
		List<ActionInfoDto> result = new ArrayList<ActionInfoDto>();
		if (user != null) {
			List<ActionEntity> actions = this.actionRepository.findByUserAndCreatedAtBetweenOrderByCreatedAtDesc(user,
					dateIntervalDto.getStartDate(), dateIntervalDto.getEndDate());
			actions.forEach(action -> {
				result.add(this.mapper.map(action, ActionInfoDto.class));
			});
		}
		return result;*/
		return null;
	}

	@Override
	public List<Long> getAllDevices(long idUser) {
		// TODO Auto-generated method stub
		//return userRepository.getAllDevicesOfUser(idUser);
		return null;
	}
	
	public List<rimenergyapi.security.dto.UserInfoDto> getAllSubaccountsByAdminId(int idAdmin) {
		return userRepo.getAllSubAccountsByAdminId(idAdmin).stream().map(u-> map2Dto(u, "")).collect(Collectors.toList());
	}
	
	private  rimenergyapi.security.dto.UserInfoDto map2Dto(rimenergyapi.security.entity.User entity, String token ) {
		rimenergyapi.security.dto.UserInfoDto userDto = new rimenergyapi.security.dto.UserInfoDto();
		userDto.setId(entity.getId());
		userDto.setRoot(entity.isRoot());
		userDto.setUsername(entity.getUsername());
		userDto.setToken(token);
		userDto.setRoles(entity.getRoles());
		userDto.setEnabled(entity.isEnable());
		return userDto;
	}
}