package rimenergyapi.userData.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import rimenergyapi.dto.RealTimePointDto;
import rimenergyapi.dto.SensorValuesDto;
import rimenergyapi.userData.model.DataRtDto;
import rimenergyapi.userData.model.GazDto;
import rimenergyapi.userData.model.IoDataDto;
import rimenergyapi.userData.model.IoRtDto;

public interface RealTimeService {
	public List<Object[]> getAllEnergyCategorie();
	
	public List<Object[]> getAllEnergyInstallation();
	
	public List<Object[]> getAllEnergyZone();

	public DataRtDto getAllEnergy(boolean isSingleInstallation) ;

	public DataRtDto getAllRecapEnergy(Timestamp startTime, Timestamp endTime);
	
	List<RealTimePointDto> findRTPointByDevice(int deviceId);
	
	public List<GazDto> getAllEnergyGaz();
	
	public List<GazDto> getAllEnergyGazDate(Timestamp startTime, Timestamp endTime );

	Map<Integer, Map<String, List<SensorValuesDto>>> getAllRtSensors();

	public Map<Integer, IoRtDto> getAllRtIo(String period);
}
