package rimenergyapi.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.dto.DeviceInfoDto;
import rimenergyapi.entity.DeviceEntity;
import rimenergyapi.repository.DeviceRepository;
import rimenergyapi.service.DeviceManagementService;
import rimenergyapi.utils.GenericUtil;

@Service
public class DeviceManagementServiceImpl implements DeviceManagementService {
	
	@Autowired
	private DeviceRepository deviceRepo;

	@Autowired
	protected ModelMapper mapper;
	

	@Override
	public List<DeviceInfoDto> getAllByTenantId(int tenantId) {
		try {
			return GenericUtil.map(mapper, deviceRepo.findByTenantId(tenantId), DeviceInfoDto.class);
		} catch (Exception e) { }
		return null;
	}

	@Override
	@Transactional
	public DeviceInfoDto updateDevice(DeviceInfoDto deviceDto) {
		try {
			Optional<DeviceEntity> entityOpt = deviceRepo.findById(deviceDto.getId());
			if(entityOpt.isPresent()) {
				return createDevice(deviceDto);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public boolean deleteDevice(int id) {
	try {
			DeviceEntity entity = deviceRepo.findById(id).get();
			deviceRepo.delete(entity);
			return true;
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	@Transactional
	public DeviceInfoDto createDevice(DeviceInfoDto deviceDto) {
		try {
			DeviceEntity device =  mapper.map(deviceDto, DeviceEntity.class); 
			device = deviceRepo.save(device);
			deviceDto = mapper.map(device, DeviceInfoDto.class);
			return deviceDto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
