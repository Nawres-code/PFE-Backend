package rimenergyapi.service.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.dto.StationInfoDto;
import rimenergyapi.entity.StationEntity;
import rimenergyapi.repository.StationRepository;
import rimenergyapi.service.StationService;

@Service
public class StationServiceImpl implements StationService {

	@Autowired
	private StationRepository stationRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Transactional
	@Override
	public StationInfoDto updateStation(StationInfoDto station) {
		StationEntity entity = stationRepo.findById(station.getId()).orElseGet(null);
		if(entity != null) {
			entity.setName(station.getName()); 
			entity.setDescription(station.getDescription());
			entity.setX(station.getX()); 
			entity.setY(station.getY()); 
			entity.setAltitude(station.getAltitude()); 
			entity = stationRepo.save(entity);
			return modelMapper.map(entity, StationInfoDto.class);
		}
		return null;
	}

}
