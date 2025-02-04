package rimenergyapi.userData.service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import rimenergyapi.userData.model.*;

public interface ArchiveService {
	public List<ArchiveTemperatureDto> getAllArchiSonde(BigInteger sondeId, Timestamp startTime, Timestamp endTime);
	
	public List<ArchiveTemperatureDto> getAllArchiveTemperature();
	
	public List<ArchiveDto> getAllArchive();
	

}
