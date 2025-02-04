package rimenergyapi.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import rimenergyapi.entity.UserAlertEntity;
import rimenergyapi.entity.Type.UserAlertType;

public interface UserAlertRepository extends JpaRepository<UserAlertEntity, Integer>, UserAlertRepositoryCustom {
	List<UserAlertEntity> findAllByTenantId(int tenantId, Sort sort);
}
