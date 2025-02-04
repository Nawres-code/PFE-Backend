package rimenergyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rimenergyapi.entity.AuthorityEntity;

public interface AuthorityRepository  extends JpaRepository <AuthorityEntity, Long> {

	List<AuthorityEntity> findByIdIn(List<Long> authorityIds);

}
