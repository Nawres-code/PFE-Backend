package rimenergyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rimenergyapi.entity.SondeEntity;


public interface SondeRepository extends JpaRepository<SondeEntity, Double> {

	@Query(value = "SELECT s.id as id, s.name as name, s.device_id, s.installation_id, s.index_Hum_Device, s.type, s.min_threshold,"
			+ " s.max_threshold, s.role, s.configuration, s.fictif_id"
			+ " FROM installation inst, sonde s " + 
			" where inst.id = s.installation_id and inst.tenant_id = :tenantId  ", nativeQuery = true)
	public List<SondeEntity> getAllSonde(@Param("tenantId") int tenantId);
	
	long countByInstallationId(int installationId);            

	
}
