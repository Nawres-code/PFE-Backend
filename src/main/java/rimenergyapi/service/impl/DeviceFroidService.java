package rimenergyapi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.entity.DeviceFroidEntity;
import rimenergyapi.repository.DeviceFroidRepository;

@Service
public class DeviceFroidService {
	
	@Autowired
	private DeviceFroidRepository deviceRepo;
	
	public List<DeviceFroidEntity> getAllDevices(){
		return deviceRepo.findAll();
	}
	
}
