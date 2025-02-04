package rimenergyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rimenergyapi.entity.PhaseEntity;

public interface PhaseRepository extends JpaRepository<PhaseEntity, Integer> {
	
	List<PhaseEntity> findByDevice_TenantId(int tenantId);  
}
