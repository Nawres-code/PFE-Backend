package rimenergyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rimenergyapi.entity.GroupsEntity;

public interface GroupRepository extends JpaRepository<GroupsEntity, Integer> { 

	List<GroupsEntity> findByTenantId(int tenantId);

}
