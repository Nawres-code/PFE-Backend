package rimenergyapi.service;

import java.util.List;

import rimenergyapi.dto.SensorDto;
import rimenergyapi.entity.SensorEntity;

public interface SensorService {

	List<SensorEntity> getAllSensors();

	SensorDto updateSensor(SensorDto sensor);

}
