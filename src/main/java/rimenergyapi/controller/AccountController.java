package rimenergyapi.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rimenergyapi.dto.DateIntervalDto;
import rimenergyapi.dto.accounts.ActionInfoDto;
import rimenergyapi.dto.accounts.AuthorityInfoDto;
import rimenergyapi.dto.accounts.UserInfoDto;
import rimenergyapi.service.AccountService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@RequestMapping("/accounts")
@ApiIgnore
@Api(value = "accounts", description = "Sub accounts Management !")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AccountController {

	@Autowired
	AccountService accountService;
	
	// =======================================================================================================
	// GET CALL
	// =======================================================================================================
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ApiOperation(value = "all sub accounts records", response = Iterable.class)
	public List<UserInfoDto> getAllAccount() {
		return accountService.getAllAccount();
	}

	@RequestMapping(value = "/authorities", method = RequestMethod.GET)
	@ApiOperation(value = "all authorities records", response = Iterable.class)
	public List<AuthorityInfoDto> getAllAuthorities() {
		return accountService.getAllAuthorities();
	}

	@RequestMapping(value = "/actions/{idUser}", method = RequestMethod.POST)
	@ApiOperation(value = "get actions", response = Iterable.class)
	public List<ActionInfoDto> getActions(@PathVariable Long idUser, @RequestBody DateIntervalDto dateIntervalDto) {
		return accountService.getActions(idUser, dateIntervalDto);
	}

	// =======================================================================================================
	// POST CALL
	// =======================================================================================================

	@RequestMapping(value = "/actions/add/{idUser}", method = RequestMethod.POST)
	@ApiOperation(value = "add action to sub account", response = Iterable.class)
	public ActionInfoDto addAction(@PathVariable Long idUser, @RequestBody ActionInfoDto payload,
			HttpServletRequest request) {
		return accountService.addAction(idUser, payload);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ApiOperation(value = "add sub account", response = Iterable.class)
	public UserInfoDto addAccount(@RequestBody UserInfoDto account) {
		return accountService.addAccount(account);
	}
	
	// =======================================================================================================
	// PUT CALL for update sub account
	// =======================================================================================================

	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ApiOperation(value = "update sub account", response = Iterable.class)
	public UserInfoDto updateAccount(@RequestBody UserInfoDto account) {
		return accountService.updateAccount(account);
	}

	// =======================================================================================================
	// DELETE CALL
	// =======================================================================================================

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "delete sub accounts")
	public Boolean deleteAccount(@PathVariable("id") Long id) {
		return accountService.deleteAccount(id);
	}

	// =======================================================================================================
	// POST CALL to enable or disable account
	// =======================================================================================================
	@RequestMapping(value = "/enabled/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "enable or disable sub account", response = Iterable.class)
	public UserInfoDto enableOrDisableAccount(@PathVariable long id) {
		return accountService.enableOrDisableAccount(id);
	}


}
