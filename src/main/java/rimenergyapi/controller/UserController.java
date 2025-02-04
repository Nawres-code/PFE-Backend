package rimenergyapi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;
import rimenergyapi.dto.generic.ValueDto;
import rimenergyapi.security.JwtTokenProvider;
import rimenergyapi.security.dto.Credentials;
import rimenergyapi.security.dto.PasswordConfirmation;
import rimenergyapi.security.dto.TokenRefreshRequest;
import rimenergyapi.security.dto.TokenRefreshResponse;
import rimenergyapi.security.dto.UserAddDto;
import rimenergyapi.security.dto.UserInfoDto;
import rimenergyapi.security.entity.RefreshToken;
import rimenergyapi.security.exception.TokenRefreshException;
import rimenergyapi.security.repository.UserRepository;
import rimenergyapi.security.service.RefreshTokenService;
import rimenergyapi.service.AccountService;
import rimenergyapi.service.impl.UserService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private RefreshTokenService refreshTokenService;
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	private UserRepository userRepository;

	/**
	 * --------------------------------------------------------------
	 * 
	 * GET CALLS
	 * 
	 * --------------------------------------------------------------
	 **/
	@ApiIgnore
	@GetMapping("/forgetcredentials/{email}")
	public void forgetCredentials(@PathVariable String email) {
		userService.forgetCredentials(email);
	}
	
	@ApiIgnore
	@GetMapping(value = "/{username}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public UserInfoDto search(@ApiParam("Username") @PathVariable String username) {
		return userService.search(username);
	}
	
	@ApiIgnore
	@GetMapping(value = "/me")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
	public UserInfoDto whoami(HttpServletRequest req) {
		return userService.whoami(req);
	}

	/**
	 * --------------------------------------------------------------
	 * 
	 * POST CALLS
	 * 
	 * --------------------------------------------------------------
	 **/
	@PostMapping("/signin")
	public UserInfoDto login(@RequestBody @Valid Credentials credentials) {
		return userService.signin(credentials);
	}
	
	@ApiIgnore
	@PostMapping("/signout/{id}")
	public void logout(@PathVariable ("id") int idTenant) {
		 userService.signOut(idTenant);
	}
	
	
	@PostMapping("/signup")
	public UserInfoDto signup(@RequestBody @Valid UserAddDto user) {
		return userService.signup(user);
	}

	/**
	 * --------------------------------------------------------------
	 * 
	 * PUT CALLS
	 * 
	 * --------------------------------------------------------------
	 **/
	@ApiIgnore
	@PutMapping("/update/user-info")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
	public ValueDto<String> updateUserInfo(@RequestBody @Valid UserInfoDto payload, HttpServletRequest req) {
		return new ValueDto<>(userService.updateUserInfo(req, payload));
	}
	@ApiIgnore
	@PutMapping("/update/user-password")
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
	public void updateUserPassword(@RequestBody @Valid PasswordConfirmation payload, HttpServletRequest req) {
		userService.updateUserPassword(req, payload);
	}

	/**
	 * --------------------------------------------------------------
	 * 
	 * DELETE CALLS
	 * 
	 * --------------------------------------------------------------
	 **/
	@ApiIgnore
	@DeleteMapping(value = "/{username}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String delete(@ApiParam("Username") @PathVariable String username) {
		userService.delete(username);
		return username;
	}

	@GetMapping(value = "/{id}/subaccounts")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public List<rimenergyapi.security.dto.UserInfoDto> getAllSubaccountsByAdminId(@PathVariable int id){
		return accountService.getAllSubaccountsByAdminId(id);
	}
	
	@GetMapping(value = "/{id}/subaccounts/loadData/{subId}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<Map<String, String>> loadSubAccountData(@PathVariable int id, @PathVariable int subId  ) {
		Map<String, String> result =new HashMap<String, String>();
		result.put("token", userService.changeSub(subId, id));
		return ResponseEntity.ok(result);
	}
	
	@PostMapping(value = "/{id}/subaccounts/loadData/{subId}")
	@PreAuthorize("hasRole('ROLE_SUPER_ADMIN')")
	public ResponseEntity<Map<String, String>> loadSubAccountData(@PathVariable int id, @PathVariable int subId, @Valid @RequestBody TokenRefreshRequest request) {
		Map<String, String> result =new HashMap<String, String>();
		result.put("token", userService.changeSub(subId, id, request.getRefreshToken()));
		return ResponseEntity.ok(result);
	}
	  
//	  @PostMapping("/signout")
//	  public ResponseEntity<?> logoutUser() {
//	    UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//	    Long userId = userDetails.getId();
//	    refreshTokenService.deleteByUserId(userId);
//	    return ResponseEntity.ok(new MessageResponse("Log out successful!"));
//	  }
	
	  @PostMapping("/refreshtoken")
	  public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
	    String requestRefreshToken = request.getRefreshToken();
	    return refreshTokenService.findByToken(requestRefreshToken)
	        .map(refreshTokenService::verifyExpiration)
	        .map(RefreshToken::getUser)
	        .map(user -> {
	          String token = jwtTokenProvider.createToken(userRepository.findById(user.getId()).get()); 
	          return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
	        }).orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Refresh token is not in database!"));
	  }
	  

	
}
