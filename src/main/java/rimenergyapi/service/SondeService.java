package rimenergyapi.service;

import java.math.BigInteger;
import java.util.List;

import rimenergyapi.dto.data.SondeInfoDto;

public interface SondeService {
	
	public SondeInfoDto addSonde(SondeInfoDto sondeInfoDto);
	
	public SondeInfoDto updateSonde(SondeInfoDto sondeInfoDto);
	
	public Boolean deleteSonde(Double id);

	public List<SondeInfoDto> getAllSonde(int tenantId);
	
	public SondeInfoDto getSondeById(Double id);
}
