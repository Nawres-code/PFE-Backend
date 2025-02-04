package rimenergyapi.security.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import rimenergyapi.security.entity.User;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

	@Autowired
	@Qualifier("masterEntityManager")
	private EntityManager em;
	
	@Override
	public List<User> getAllSubAccountsByAdminId(int adminID) {
		List<User> subAccounts = new ArrayList<>();
		String sql = "select * from user where root_Id = "+ adminID;
		try {
	 		subAccounts = em.createNativeQuery(sql, User.class).getResultList();
		} catch (Exception e) { }
		return subAccounts;
	}

	
}
