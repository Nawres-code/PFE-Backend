package rimenergyapi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.dto.data.InstallationInfoDto;
import rimenergyapi.entity.InstallationEntity;
import rimenergyapi.entity.InstallationView;
import rimenergyapi.entity.ZonesEntity;
import rimenergyapi.exception.ResourceNotFoundException;
import rimenergyapi.repository.InstallationRepository;
import rimenergyapi.service.InstallationService;

@Service
public class InstallationServiceImpl implements InstallationService {

	@Autowired
	InstallationRepository installationRepository;

	@Autowired
	protected ModelMapper mapper;
	
	@Autowired
	private CurrentTenantIdentifierResolver tenantIdentifier;

	@Transactional
	@Override
	public InstallationInfoDto addInstallation(InstallationInfoDto installationInfoDto) {
		InstallationEntity  entity = mapper.map(installationInfoDto, InstallationEntity.class);
		entity.setZones(new ZonesEntity(installationInfoDto.getZoneId()));
		entity.setTenantId(Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()));
		entity.setType(installationInfoDto.getType());
		entity.setGpsLat("");
		entity.setGpsLon("");
		entity = installationRepository.save(entity);
		return	mapDto(entity);
	}

	@Transactional
	@Override
	public InstallationInfoDto updateInstallation(InstallationInfoDto installationInfoDto) {
		// TODO Auto-generated method stub
		if (installationInfoDto != null) {
			Optional<InstallationEntity> installationOpt = installationRepository.findById(installationInfoDto.getId());
			if (installationOpt.isPresent()) {
				InstallationEntity installationUpdate = installationOpt.get();
				installationUpdate.setName(installationInfoDto.getName());
				installationUpdate.setEnabled(installationInfoDto.isEnabled());
				installationUpdate.setZones(new ZonesEntity(installationInfoDto.getZoneId()));
				installationUpdate = installationRepository.saveAndFlush(installationUpdate);
				return mapDto(installationUpdate);
			}
		}
		return null;
	}

	@Override
	@Transactional
	public Boolean deleteInstallation(int id) {
		InstallationEntity instEntity = this.installationRepository.findById(id).get();
		if (instEntity == null) {
			throw new ResourceNotFoundException("installation " + id + "n'existe pas");
		}
		try {
			installationRepository.delete(instEntity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<InstallationInfoDto> getAllInstallation(int tenantId) {
		List<InstallationInfoDto> dtos = new ArrayList<InstallationInfoDto>();
		List<InstallationEntity> entities = installationRepository.findByTenantId(tenantId);
		for (InstallationEntity eInst : entities)  {
			try {
				dtos.add(mapDto(eInst));
			} catch (Exception e) {}
		}
		return dtos;
	}

	@Transactional
	@Override
	public Boolean toggleOutputMode(int idInstallation, boolean oldAutoMode) {
		int rowCount = 0;
		rowCount = installationRepository.updateOutputAutoMode(idInstallation, !oldAutoMode);
		if(rowCount == 1) { return !oldAutoMode; }
		return oldAutoMode;
	}
	
	@Override
	public InstallationView getInstallationById( int installationId) {
		try {
			return installationRepository.getOne(installationId);
		} catch (Exception e) { }
		return null;
	}
	
	private InstallationInfoDto mapDto(InstallationEntity entity) {
		InstallationInfoDto dto = new InstallationInfoDto();
		try {
			dto = mapper.map(entity, InstallationInfoDto.class);
			dto.setZoneId(entity.getZones().getIdZone());
			dto.setZoneName(entity.getZones().getName());
		} catch (Exception e) {
		}
		return dto;
	}

}
