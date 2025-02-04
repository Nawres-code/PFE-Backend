package rimenergyapi.security.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rimenergyapi.security.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> , UserRepositoryCustom {

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	User findByUsernameOrEmail(String username, String email);

	@Query("select u from User u where (u.username = :username or u.email = :email) and u.enable > 0")
	User findEnableUser(@Param("username") String username, @Param("email") String email);

	User findByEmail(String email);
	
	User findById(int id);

	@Transactional
	void deleteByUsername(String username);
	
}
