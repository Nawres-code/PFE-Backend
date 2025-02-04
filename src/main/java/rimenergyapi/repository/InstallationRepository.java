package rimenergyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rimenergyapi.entity.InstallationEntity;

public interface InstallationRepository extends JpaRepository<InstallationEntity, Integer> { 

	@Query(value = "SELECT * FROM installation inst " + 
			"where inst.tenant_id = :tenantId ", nativeQuery = true)
	public List<InstallationEntity> getAllInstallation(@Param("tenantId") int tenantId);
	
	@Modifying
	@Query(nativeQuery = true,  value = "UPDATE installation SET outputs_auto_mode = :mode WHERE id = :id")
	int updateOutputAutoMode(@Param("id") int idInstallation, @Param("mode") boolean autoMode);
	
	@Query(value = "SELECT id FROM installation inst " + 
			"where inst.tenant_id = :tenantId ", nativeQuery = true)
	public List<Integer> getAllInstallationIds(@Param("tenantId") int tenantId);
	
	@Query(value = "SELECT id FROM  sonde where installation_id = :installationId ", nativeQuery = true)
	public List<Double> getAllSondeIdByInstallationId(@Param("installationId")double installationId);
	
	List<InstallationEntity> findByTenantId(int tenantId);

}
