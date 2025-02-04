package rimenergyapi.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import rimenergyapi.dto.SensorValuesDto;
import rimenergyapi.userData.model.ArchiveMetosDto;

public interface ArchiveMetosService {
	
	HashMap<Integer, HashMap<String, Map<String, TreeSet<SensorValuesDto>>>> findSensorsHistoryByStationId
	(String stationId, List<String> sensorIds, Timestamp from, Timestamp to);
}
