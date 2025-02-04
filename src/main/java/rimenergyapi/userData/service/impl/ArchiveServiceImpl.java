package rimenergyapi.userData.service.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rimenergyapi.userData.model.ArchiveDto;
import rimenergyapi.userData.model.ArchiveTemperatureDto;
import rimenergyapi.userData.model.RtStatisticalDto;
import rimenergyapi.userData.repository.ArchiveRepository;
import rimenergyapi.userData.service.ArchiveService;

@Service
public class ArchiveServiceImpl implements ArchiveService {
	@Autowired
	ArchiveRepository archiveRepository;

	@Override
	public List<ArchiveTemperatureDto> getAllArchiSonde(BigInteger sondeId, Timestamp startTime, Timestamp endTime) {
		return archiveRepository.findListInTemperature(sondeId, startTime, endTime);
	}

	@Override
	public List<ArchiveTemperatureDto> getAllArchiveTemperature() {
		return archiveRepository.findAllArchiveTemperature();
	}

	@Override
	public List<ArchiveDto> getAllArchive() {
		return archiveRepository.findAllArchive();
	}

}
