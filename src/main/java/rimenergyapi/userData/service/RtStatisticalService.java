package rimenergyapi.userData.service;

import java.sql.Timestamp;
import java.util.Map;

import rimenergyapi.userData.model.RtStatisticalDto;

public interface RtStatisticalService {
	
	Map<Integer, Map<String, Double>> getRtStatistical(String grouping, String timing, String timeoffset);
}
