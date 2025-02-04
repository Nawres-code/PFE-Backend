package rimenergyapi.userData.service.impl;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.dto.SensorValuesDto;
import rimenergyapi.service.ArchiveMetosService;
import rimenergyapi.userData.model.ArchiveMetosDto;
import rimenergyapi.userData.repository.ArchiveMetosRepository;

@Service
public class ArchiveMetosServiceImpl implements ArchiveMetosService {

	@Autowired
	ArchiveMetosRepository archiveMetosRepository;

	@Override
	public HashMap<Integer, HashMap<String, Map<String, TreeSet<SensorValuesDto>>>> findSensorsHistoryByStationId(
			String stationId, List<String> sensorIds, Timestamp from, Timestamp to) {
		HashMap<Integer, HashMap<String, Map<String, TreeSet<SensorValuesDto>>>> rtMap = null;
		try {
			List<SensorValuesDto> rtSensors = archiveMetosRepository.findSensorsByTimeRangeAndStationId(stationId, sensorIds, from, to);
			rtMap = rtSensors.stream()
			.collect(Collectors.groupingBy(SensorValuesDto::getInstallationId, HashMap::new,
					Collectors.groupingBy(SensorValuesDto::getStationId, HashMap::new,
							Collectors.groupingBy(SensorValuesDto::getSensorId, Collectors.toCollection(
						            () -> new TreeSet<>(Comparator.comparing(SensorValuesDto::getLastTime))
							        )))));
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return rtMap;
	}
	
	

}
