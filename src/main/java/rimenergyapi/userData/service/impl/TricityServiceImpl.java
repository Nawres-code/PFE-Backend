package rimenergyapi.userData.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rimenergyapi.userData.model.RtTricityDto;
import rimenergyapi.userData.repository.TricityRespository;
import rimenergyapi.userData.service.TricityService;

@Service
public class TricityServiceImpl implements TricityService{

	@Autowired
	TricityRespository tricityRespository;
	
	@Override
	public List<RtTricityDto> getAllTricity() {
		return tricityRespository.findAllRtTricity();
	}
}
