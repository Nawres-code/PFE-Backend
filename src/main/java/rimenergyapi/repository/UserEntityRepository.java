package rimenergyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rimenergyapi.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
	public UserEntity findByUsername (String username);
}