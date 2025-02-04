package rimenergyapi.service;

import java.util.List;

import rimenergyapi.dto.DateIntervalDto;
import rimenergyapi.dto.accounts.ActionInfoDto;
import rimenergyapi.dto.accounts.AuthorityInfoDto;
import rimenergyapi.dto.accounts.UserInfoDto;
import rimenergyapi.security.entity.User;

public interface AccountService {
	
	List<UserInfoDto> getAllAccount();

	List<AuthorityInfoDto> getAllAuthorities();

	Boolean deleteAccount(Long id);

	UserInfoDto addAccount(UserInfoDto account);

	UserInfoDto updateAccount(UserInfoDto account);
	
	UserInfoDto enableOrDisableAccount(long id);

	ActionInfoDto addAction(Long id, ActionInfoDto payload);

	List<ActionInfoDto> getActions(Long userId, DateIntervalDto dateIntervalDto);
	
	List<Long> getAllDevices(long idUser);
	
	List<rimenergyapi.security.dto.UserInfoDto> getAllSubaccountsByAdminId(int idAdmin);

}
