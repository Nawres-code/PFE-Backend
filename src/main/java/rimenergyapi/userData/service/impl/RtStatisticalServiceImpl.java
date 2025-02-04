package rimenergyapi.userData.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.userData.model.RecordDto;
import rimenergyapi.userData.repository.DayRepEnergyRepository;
import rimenergyapi.userData.repository.HourRepEnergyRepository;
import rimenergyapi.userData.service.RtStatisticalService;

@Service
public class RtStatisticalServiceImpl implements RtStatisticalService {

	@Autowired
	private DayRepEnergyRepository dayRepo;
	 
	@Autowired
	private HourRepEnergyRepository hourRepo;
	
	
	@Override
	public Map<Integer, Map<String, Double>> getRtStatistical(String grouping, String timing, String timeoffset) {
		Map<Integer, Map<String, Double>> result = new HashMap<Integer, Map<String, Double>>();
		List<RecordDto> todayRecords = null;
		List<RecordDto> yesterdayRecords = null;
		
		switch(grouping) {
		case "zone":
			switch(timing) {
				case "lastday":
					todayRecords = dayRepo.findAllDayRepEnergyByZone("lastday", false);
					yesterdayRecords = dayRepo.findAllDayRepEnergyByZone("lastday", true);
				 break;	
				case "thisday":
					todayRecords = dayRepo.findAllDayRepEnergyByZone("thisday", false);
					yesterdayRecords = dayRepo.findAllDayRepEnergyByZone("thisday", true);
				 break;	
				case "lasthour":
					todayRecords = hourRepo.findAllHourRepEnergyByZone("lasthour", false);
					yesterdayRecords = hourRepo.findAllHourRepEnergyByZone("lasthour", true);
				 break;	
			}
			break;	
			
		case "installation":
			switch(timing) {
			case "lastday":
				todayRecords = dayRepo.findAllDayRepEnergyByInstallation("lastday", false);
				yesterdayRecords = dayRepo.findAllDayRepEnergyByInstallation("lastday", true);
			 break;	
			case "thisday":
				todayRecords = dayRepo.findAllDayRepEnergyByInstallation("thisday", false);
				yesterdayRecords = dayRepo.findAllDayRepEnergyByInstallation("thisday", true);
				break;
			case  "lasthour":
				todayRecords = hourRepo.findAllHourRepEnergyByInstallation("lasthour", false);
				yesterdayRecords = hourRepo.findAllHourRepEnergyByInstallation("lasthour", true);
				break;	
			}
			break;	
		}
		// Map<Integer, Map<String, Double>>
		result = todayRecords.stream()
				.collect(Collectors.groupingBy(RecordDto::getGroupId, HashMap::new,
						Collectors.mapping(RecordDto::getSumAct, Collectors.toMap(value -> "val1", value -> value))));
		
		for(RecordDto record : yesterdayRecords) {
			if(result.get(record.getGroupId()) == null) {
				result.put(record.getGroupId(), new HashMap<String, Double>());
			}
			result.get(record.getGroupId()).put("val2", record.getSumAct());
		}
		return result;
	}

}
