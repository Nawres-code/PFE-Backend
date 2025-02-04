package rimenergyapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.dto.data.SondeInfoDto;
import rimenergyapi.entity.DeviceEntity;
import rimenergyapi.entity.InstallationEntity;
import rimenergyapi.entity.SondeEntity;
import rimenergyapi.exception.ResourceNotFoundException;
import rimenergyapi.repository.DeviceRepository;
import rimenergyapi.repository.SondeRepository;
import rimenergyapi.service.SondeService;

@Service
public class SondeServiceImpl implements SondeService {

	@Autowired
	SondeRepository sondeRepository;

	@Autowired
	DeviceRepository deviceRepo;
	
	@Autowired
	protected ModelMapper mapper;
	
	@Autowired
	private CurrentTenantIdentifierResolver tenantIdentifier;

	@Override
	public List<SondeInfoDto> getAllSonde(int tenantId) {
		// TODO Auto-generated method stub
		List<SondeInfoDto> SondeInfoDtos = new ArrayList<SondeInfoDto>();
		sondeRepository.getAllSonde(tenantId).forEach(
		s-> {
			SondeInfoDto dto = this.mapper.map(s, SondeInfoDto.class);
			SondeInfoDtos.add(dto);
		});
		return SondeInfoDtos;
	}

	@Override
	@Transactional
	public SondeInfoDto addSonde(SondeInfoDto sondeInfoDto) {
		// TODO Auto-generated method stub
		SondeEntity sonde = this.mapper.map(sondeInfoDto, SondeEntity.class);

		try {
			DeviceEntity temperatureDevice = 
					deviceRepo.findByInstallationIdAndType(sondeInfoDto.getInstallationId(),
							sondeInfoDto.getConfiguration().equals("temperature;humidity;battery")?"temperature_mqtt":"temperature_old");
			sonde.setId(sondeInfoDto.getInstallationId()*100.0 + 1+ sondeRepository.countByInstallationId(sondeInfoDto.getInstallationId()));
			sonde.setIndexHumDevice(0);
			sonde.setDevice(temperatureDevice);
			if(temperatureDevice == null) {
				temperatureDevice = new DeviceEntity();
				temperatureDevice.setInstallation(sonde.getInstallation());
				temperatureDevice.setPeriod(15);
				temperatureDevice.setIsFictif(true);
				temperatureDevice.setType(sondeInfoDto.getConfiguration().equals("temperature;humidity;battery")?"temperature_mqtt":"temperature_old");
				temperatureDevice.setProtocol(temperatureDevice.getType().equals("temperature_old")?"0102040404042021040404042324252627282D2E2F21210404":"");
				temperatureDevice.setPool(temperatureDevice.getType().equals("temperature_old")?"2":"7");
				temperatureDevice.setEnabled(true);
				temperatureDevice.setTenantId(Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()));
				sonde.setDevice(deviceRepo.save(temperatureDevice));
			}
		} catch (Exception e) {
		}
		
		SondeEntity sondeAdded = this.sondeRepository.save(sonde);
		return this.mapper.map(sondeAdded, SondeInfoDto.class);		
	}

	@Transactional
	@Override
	public SondeInfoDto updateSonde(SondeInfoDto sondeInfoDto) {
		// TODO Auto-generated method stub
		if (sondeInfoDto != null) {
			Optional<SondeEntity> sondeOpt = sondeRepository.findById(new Double(sondeInfoDto.getId()));
			if (sondeOpt.isPresent()) {
				SondeEntity sondeUpdate = sondeOpt.get();
				sondeUpdate.setMaxThreshold(sondeInfoDto.getMaxThreshold());
				sondeUpdate.setMinThreshold(sondeInfoDto.getMinThreshold());
				sondeUpdate.setName(sondeInfoDto.getName());
				sondeUpdate.setInstallation(new InstallationEntity(sondeInfoDto.getInstallationId()));
				sondeUpdate.setFictifId(sondeInfoDto.getFictifId());
				sondeUpdate.setConfiguration(sondeInfoDto.getConfiguration());
				sondeUpdate = sondeRepository.saveAndFlush(sondeUpdate);
				return this.mapper.map(sondeUpdate, SondeInfoDto.class);

//				SondeInfoDto result = new SondeInfoDto(String.valueOf(sondeUpdate.getId()), sondeUpdate.getName(),
//						sondeUpdate.getIndexHumDevice(), sondeUpdate.getType(), sondeUpdate.getMinThreshold(),
//						sondeUpdate.getMaxThreshold(), sondeUpdate.getRole());
//				return result;
			}
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean deleteSonde(Double id) {
		SondeEntity sondeOld = this.sondeRepository.findById(id).get();
		if (sondeOld == null) {
			throw new ResourceNotFoundException("Sonde" + id + "n'existe pas");
		}
		try {
			sondeRepository.delete(sondeOld);
			return true;
		} catch (Exception e) {
			return false;

		}
	}

	@Override
	public SondeInfoDto getSondeById(Double id) {
		Optional<SondeEntity> sondeOpt = sondeRepository.findById(id);
		return sondeOpt.isPresent() ? this.mapper.map(sondeOpt.get(), SondeInfoDto.class) : null;
	}

}
