package rimenergyapi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.dto.SensorDto;
import rimenergyapi.entity.SensorEntity;
import rimenergyapi.repository.SensorRepository;
import rimenergyapi.service.SensorService;

@Service
public class SensorServiceImpl implements SensorService {
	
	@Autowired
	private SensorRepository sensorRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<SensorEntity> getAllSensors() {
		try {
			return sensorRepo.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public SensorDto updateSensor(SensorDto sensor) {
		SensorEntity entity = sensorRepo.findById(sensor.getId()).orElseGet(null);
		if(entity != null) {
			entity.setName(sensor.getName()); 			
			entity.setUnit(sensor.getUnit());
			entity.setAggr(sensor.getAggr());
			entity = sensorRepo.save(entity);
			return modelMapper.map(entity, SensorDto.class);
		}
		return null;
	}

}
