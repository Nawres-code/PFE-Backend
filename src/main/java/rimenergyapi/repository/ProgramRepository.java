package rimenergyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rimenergyapi.entity.ProgramEntity;

public interface ProgramRepository extends JpaRepository<ProgramEntity, Integer> {

	List<ProgramEntity> findAllByTenantId(int tenantId);

}
