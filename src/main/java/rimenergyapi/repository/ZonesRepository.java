package rimenergyapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import rimenergyapi.dto.data.ZoneInfoDto;
import rimenergyapi.entity.ZonesEntity;

@Repository
public interface ZonesRepository extends JpaRepository<ZonesEntity,Integer> {
	
	@Query(value = "SELECT o from ZonesEntity o where tenant_id = :tenantId ")
	public List<ZonesEntity> getZoneByTenantId(@Param("tenantId") int tenantId, Sort sort);
	
	public List<ZonesEntity> getZoneByTenantId(@Param("tenantId") int tenantId);
	
	public List<ZonesEntity> getZoneByTenantIdOrderByNameAsc(@Param("tenantId") int tenantId);
	
	@Query(value = "SELECT o.idZone, o.name, o.tenantId FROM ZonesEntity o WHERE o.tenantId = :tenantId")
	List<ZoneInfoDto> getZonesInfoByTenantId(@Param("tenantId") int tenantId);
	
	@Modifying
	@Query(value="update ZonesEntity z set z.name = :name where z.idZone = :idZone ")
	void updateZoneName(@Param("name") String name, @Param("idZone") int idZone);
	
	@Query(value = "SELECT o.idZone, o.name, o.tenantId FROM ZonesEntity o WHERE o.idZone = :idZone")
	Object[] getZoneInfoById(@Param("idZone") int idZone);
	
	List<ZonesEntity> findByTenantId(int tenantId);
}
