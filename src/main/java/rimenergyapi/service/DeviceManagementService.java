package rimenergyapi.service;

import java.util.List;

import rimenergyapi.dto.DeviceInfoDto;

public interface DeviceManagementService {

	List<DeviceInfoDto> getAllByTenantId(int tenantId);

	DeviceInfoDto updateDevice(DeviceInfoDto deviceDto);

	boolean deleteDevice(int id);

	DeviceInfoDto createDevice(DeviceInfoDto deviceDto);

}
