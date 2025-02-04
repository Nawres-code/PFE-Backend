package rimenergyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rimenergyapi.entity.DeviceEntity;
import rimenergyapi.userData.repository.DeviceRepositoryCustom;

public interface DeviceRepository extends JpaRepository<DeviceEntity, Integer>, DeviceRepositoryCustom {
	
	DeviceEntity findByInstallationIdAndType(int installationId, String type);
	List<DeviceEntity> findByTenantId(int tenantId);
	
}
