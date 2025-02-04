package rimenergyapi.userData.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.userData.model.RecordDto;
import rimenergyapi.userData.model.RtEnergyDto;
import rimenergyapi.userData.repository.EnergyRepository;
import rimenergyapi.userData.service.EnergyService;

@Service
public class EnergyServiceImpl implements EnergyService {

	@Autowired
	EnergyRepository energyRepository;

	@Override
	public List<RtEnergyDto> getAllEnergyInstallations(long installationIds) {
		return energyRepository.findListRtEnergy(installationIds);
	}

	@Override
	public List<RtEnergyDto> getAllEnergy() {
		return energyRepository.findAllRTtEnergy();
	}

	/*@Override
	public Map<Integer, List<RecordDto>> getAllRepEnergy5min(int[] groupIds, Timestamp startDate, Timestamp endDate) {
		List<Object[]> records = energyRepository.finAllRepEnergy5min(groupIds, startDate, endDate);
		Map<Integer, List<RecordDto>> mapRecord = new HashMap<Integer, List<RecordDto>>();
		for (int i = 0; i < records.size(); i++) {
			RecordDto record = new RecordDto((Integer) records.get(i)[0], (Double) records.get(i)[1],
					(Timestamp) records.get(i)[2]);
			if (!mapRecord.containsKey(record.getGroupId())) {
				mapRecord.put(record.getGroupId(), new ArrayList<RecordDto>());
			}
			mapRecord.get(record.getGroupId()).add(record);
		}
		return mapRecord;

	}

	@Override
	public Map<Integer, List<RecordDto>> getAllRepEnergyDay(int[] groupIds, Timestamp startDate, Timestamp endDate) {
		List<Object[]> records = energyRepository.finAllRepEnergyDay(groupIds, startDate, endDate);
		Map<Integer, List<RecordDto>> mapRecord = new HashMap<Integer, List<RecordDto>>();
		for (int i = 0; i < records.size(); i++) {
			RecordDto record = new RecordDto((Integer) records.get(i)[0], (Double) records.get(i)[1],
					(Timestamp) records.get(i)[2]);
			if (!mapRecord.containsKey(record.getGroupId())) {
				mapRecord.put(record.getGroupId(), new ArrayList<RecordDto>());
			}
			mapRecord.get(record.getGroupId()).add(record);
		}
		return mapRecord;
	}

	@Override
	public Map<Integer, List<RecordDto>> getAllRepEnergyHours(int[] groupIds, Timestamp startDate, Timestamp endDate) {
		List<Object[]> records = energyRepository.finAllRepEnergyHours(groupIds, startDate, endDate);
		Map<Integer, List<RecordDto>> mapRecord = new HashMap<Integer, List<RecordDto>>();
		for (int i = 0; i < records.size(); i++) {
			RecordDto record = new RecordDto((Integer) records.get(i)[0], (Double) records.get(i)[1],
					(Timestamp) records.get(i)[2]);
			if (!mapRecord.containsKey(record.getGroupId())) {
				mapRecord.put(record.getGroupId(), new ArrayList<RecordDto>());
			}
			mapRecord.get(record.getGroupId()).add(record);
		}
		return mapRecord;
	}

	@Override
	public Map<Integer, List<RecordDto>> getAllRepEnergyMonth(int[] groupIds, Timestamp startDate, Timestamp endDate) {
		List<Object[]> records = energyRepository.finAllRepEnergyMonth(groupIds, startDate, endDate);
		Map<Integer, List<RecordDto>> mapRecord = new HashMap<Integer, List<RecordDto>>();
		for (int i = 0; i < records.size(); i++) {
			RecordDto record =new RecordDto((Integer) records.get(i)[0], (Double) records.get(i)[1],
					(Timestamp) records.get(i)[2]);
			if (!mapRecord.containsKey(record.getGroupId())) {
				mapRecord.put(record.getGroupId(), new ArrayList<RecordDto>());
			}
			mapRecord.get(record.getGroupId()).add(record);
		}
		return mapRecord;
	}
*/
	@Override
	public Map<Integer, Map<Timestamp, Map<String, Double>>> getAllRepEnergy(String groupedBy, int[] ids,
			Timestamp startDate, Timestamp endDate, String timeBack, String timeStep) {

		Map<Integer, Map<Timestamp, Map<String, Double>>> result = new HashMap<Integer, Map<Timestamp, Map<String, Double>>>();
		List<RecordDto> jRecords = null;
		List<RecordDto> j_1Records = null;
		
		switch (groupedBy) {
		case "zone":
			switch (timeStep) {
			case "5min":
				jRecords = energyRepository.findAll5MinRepEnergyByZoneRange(ids, startDate, endDate, null);
				j_1Records = energyRepository.findAll5MinRepEnergyByZoneRange(ids, startDate, endDate, timeBack );
				break;
			case "day":
				jRecords = energyRepository.findAllDayRepEnergyByZoneRange(ids, startDate, endDate, null);
				j_1Records = energyRepository.findAllDayRepEnergyByZoneRange(ids, startDate, endDate, timeBack);
				break;
			case "hour":
				jRecords = energyRepository.findAllHourRepEnergyByZoneRange(ids, startDate, endDate, null);
				j_1Records = energyRepository.findAllHourRepEnergyByZoneRange(ids, startDate, endDate, timeBack);
				break;
			}
			break;
		case "installation":
			switch (timeStep) {
			case "5min":
				jRecords = energyRepository.findAll5MinRepEnergyByInstallationRange(ids, startDate, endDate, null);
				j_1Records = energyRepository.findAll5MinRepEnergyByInstallationRange(ids, startDate, endDate, timeBack);
				break;
			case "day":
				jRecords = energyRepository.findAllDayRepEnergyByInstallationRange(ids, startDate, endDate, null);
				j_1Records = energyRepository.findAllDayRepEnergyByInstallationRange(ids, startDate, endDate, timeBack);
				break;
			case "hour":
				jRecords = energyRepository.findAllHourRepEnergyByInstallationRange(ids, startDate, endDate, null);
				j_1Records = energyRepository.findAllHourRepEnergyByInstallationRange(ids, startDate, endDate, timeBack);
				break;
			}
			break;	
		case "group":
			switch (timeStep) {
			case "5min":
				jRecords = energyRepository.findAll5MinRepEnergyByGroupRange(ids, startDate, endDate, null);
				j_1Records = energyRepository.findAll5MinRepEnergyByGroupRange(ids, startDate, endDate, timeBack);
				break;
			case "day":
				jRecords = energyRepository.findAllDayRepEnergyByGroupRange(ids, startDate, endDate, null);
				j_1Records = energyRepository.findAllDayRepEnergyByGroupRange(ids, startDate, endDate, timeBack);
				break;
			case "hour":
				jRecords = energyRepository.findAllHourRepEnergyByGroupRange(ids, startDate, endDate, null);
				j_1Records = energyRepository.findAllHourRepEnergyByGroupRange(ids, startDate, endDate, timeBack);
				break;
			}
			break;
		}
		result = jRecords.stream()
				.collect(Collectors.groupingBy(RecordDto::getGroupId, HashMap::new, Collectors.groupingBy(
						RecordDto::getTime, HashMap::new,
						Collectors.mapping(RecordDto::getSumAct, Collectors.toMap(value -> "j", value -> value)))));
		
		for(RecordDto record : j_1Records) {
			if(result.get(record.getGroupId()) == null) {
				result.put(record.getGroupId(), new HashMap<Timestamp, Map<String, Double>>());
				result.get(record.getGroupId()).put(record.getTime(), new HashMap<String, Double>());
			}
			else if(result.get(record.getGroupId()).get(record.getTime()) == null) {
				result.get(record.getGroupId()).put(record.getTime(), new HashMap<String, Double>());
			}
			result.get(record.getGroupId()).get(record.getTime()).put("j-1", record.getSumAct());
		}
		return result;
	}

	@Override
	public Map<Integer, List<RecordDto>> getAllRepEnergy5minByType(int[] groupIds, Timestamp startDate, Timestamp endDate, String type) {
		
		List<Object[]> records = energyRepository.finAllRepEnergy5minByType(groupIds, startDate, endDate, type);
		
		
		Map<Integer, List<RecordDto>> mapRecord = new HashMap<Integer, List<RecordDto>>();
		for (int i = 0; i < records.size(); i++) {
			RecordDto record = new RecordDto((Integer) records.get(i)[0], (Double) records.get(i)[1],
					(Timestamp) records.get(i)[2]);
			if (!mapRecord.containsKey(record.getGroupId())) {
				mapRecord.put(record.getGroupId(), new ArrayList<RecordDto>());
			}
			mapRecord.get(record.getGroupId()).add(record);
		}
		return mapRecord;

	}

	@Override
	public Map<Integer, List<RecordDto>> getAllRepEnergyRowByType(int[] groupIds, Timestamp startDate, Timestamp endDate, String type) {

		List<Object[]> records = energyRepository.finAllRepEnergyRowByType(groupIds, startDate, endDate, type);


		Map<Integer, List<RecordDto>> mapRecord = new HashMap<Integer, List<RecordDto>>();
		for (int i = 0; i < records.size(); i++) {
			RecordDto record = new RecordDto((Integer) records.get(i)[0], (Double) records.get(i)[1],
					(Timestamp) records.get(i)[2]);
			if (!mapRecord.containsKey(record.getGroupId())) {
				mapRecord.put(record.getGroupId(), new ArrayList<RecordDto>());
			}
			mapRecord.get(record.getGroupId()).add(record);
		}
		return mapRecord;

	}

	@Override
	public Map<Integer, List<RecordDto>> getAllRepEnergyDayByType(int[] groupIds, Timestamp startDate, Timestamp endDate, String type) {
		List<Object[]> records = energyRepository.finAllRepEnergyDayByType(groupIds, startDate, endDate, type);
		Map<Integer, List<RecordDto>> mapRecord = new HashMap<Integer, List<RecordDto>>();
		for (int i = 0; i < records.size(); i++) {
			RecordDto record = new RecordDto((Integer) records.get(i)[0], (Double) records.get(i)[1],
					(Timestamp) records.get(i)[2]);
			if (!mapRecord.containsKey(record.getGroupId())) {
				mapRecord.put(record.getGroupId(), new ArrayList<RecordDto>());
			}
			mapRecord.get(record.getGroupId()).add(record);
		}
		return mapRecord;
	}

	@Override
	public Map<Integer, List<RecordDto>> getAllRepEnergyHoursByType(int[] groupIds, Timestamp startDate, Timestamp endDate, String type) {
		List<Object[]> records = energyRepository.finAllRepEnergyHoursByType(groupIds, startDate, endDate, type);
		Map<Integer, List<RecordDto>> mapRecord = new HashMap<Integer, List<RecordDto>>();
		for (int i = 0; i < records.size(); i++) {
			RecordDto record = new RecordDto((Integer) records.get(i)[0], (Double) records.get(i)[1],
					(Timestamp) records.get(i)[2]);
			if (!mapRecord.containsKey(record.getGroupId())) {
				mapRecord.put(record.getGroupId(), new ArrayList<RecordDto>());
			}
			mapRecord.get(record.getGroupId()).add(record);
		}
		return mapRecord;
	}
	
	@Override
	public Map<Integer, List<RecordDto>> getAllRepEnergyMonthByType(int[] groupIds, Timestamp startDate, Timestamp endDate, String type) {
		List<Object[]> records = energyRepository.finAllRepEnergyMonthByType(groupIds, startDate, endDate, type);
		Map<Integer, List<RecordDto>> mapRecord = new HashMap<Integer, List<RecordDto>>();
		for (int i = 0; i < records.size(); i++) {
			RecordDto record =new RecordDto((Integer) records.get(i)[0], (Double) records.get(i)[1],
					(Timestamp) records.get(i)[2]);
			if (!mapRecord.containsKey(record.getGroupId())) {
				mapRecord.put(record.getGroupId(), new ArrayList<RecordDto>());
			}
			mapRecord.get(record.getGroupId()).add(record);
		}
		return mapRecord;
	}
}
