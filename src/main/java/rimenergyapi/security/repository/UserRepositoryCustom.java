package rimenergyapi.security.repository;

import java.util.List;

import rimenergyapi.security.entity.User;

public interface UserRepositoryCustom {

	List<User> getAllSubAccountsByAdminId(int adminID) ;

}
