package rimenergyapi.userData.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import rimenergyapi.userData.model.RecordDto;
import rimenergyapi.userData.model.RtEnergyDto;

public interface EnergyService {

	public List<RtEnergyDto> getAllEnergyInstallations(long installationIds);

	public List<RtEnergyDto> getAllEnergy();

	/*public Map<Integer, List<RecordDto>> getAllRepEnergy5min(int[] groupIds, Timestamp startDate, Timestamp endDate);

	public Map<Integer, List<RecordDto>> getAllRepEnergyDay(int[] groupIds, Timestamp startDate, Timestamp endDate);

	public Map<Integer, List<RecordDto>> getAllRepEnergyHours(int[] groupIds, Timestamp startDate, Timestamp endDate);

	public Map<Integer, List<RecordDto>> getAllRepEnergyMonth(int[] groupIds, Timestamp startDate, Timestamp endDate);*/
	
	public Map<Integer, List<RecordDto>> getAllRepEnergy5minByType(int[] groupIds, Timestamp startDate, Timestamp endDate, String type);

	Map<Integer, List<RecordDto>> getAllRepEnergyRowByType(int[] groupIds, Timestamp startDate, Timestamp endDate, String type);


	public Map<Integer, List<RecordDto>> getAllRepEnergyDayByType(int[] groupIds, Timestamp startDate, Timestamp endDate, String type);

	public Map<Integer, List<RecordDto>> getAllRepEnergyHoursByType(int[] groupIds, Timestamp startDate, Timestamp endDate, String type);

	public Map<Integer, List<RecordDto>> getAllRepEnergyMonthByType(int[] groupIds, Timestamp startDate, Timestamp endDate, String type);

	Map<Integer, Map<Timestamp, Map<String, Double>>> getAllRepEnergy(String groupedBy, int[] ids,
			Timestamp startDate, Timestamp endDate, String timeBack, String timeStep);

}
