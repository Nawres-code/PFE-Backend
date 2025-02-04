package rimenergyapi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.dto.data.ZoneInfoDto;
import rimenergyapi.entity.ZonesEntity;
import rimenergyapi.repository.ZonesRepository;
import rimenergyapi.service.ZoneService;
import rimenergyapi.utils.GenericUtil;

@Service
public class ZoneServiceImpl implements ZoneService {
	
	@Autowired
	private ZonesRepository zoneRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public List<ZoneInfoDto> getAllZone(int tenantId) {
		try {
			return GenericUtil.map(mapper, zoneRepo.findByTenantId(tenantId), ZoneInfoDto.class);
		} catch (Exception e) {	}
		return null;
	}
	
	@Override
	public ZoneInfoDto getZoneById(int id) {
		try {
			return mapper.map(zoneRepo.findById(id).get(), ZoneInfoDto.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	@Override
	public ZoneInfoDto addZone(ZoneInfoDto zoneInfoDto) {
		try {
			ZonesEntity zone = this.mapper.map(zoneInfoDto, ZonesEntity.class);
			zone = this.zoneRepo.save(zone);
			return this.mapper.map(zone, ZoneInfoDto.class);
		} catch (Exception e) {
			return null;
		}
	}

	@Transactional
	public ZoneInfoDto updateZone(ZoneInfoDto zoneInfoDto) {
		try {
			if(zoneRepo.existsById(zoneInfoDto.getIdZone())) {
				ZonesEntity zone = this.mapper.map(zoneInfoDto, ZonesEntity.class);
				zone = this.zoneRepo.save(zone);
				return this.mapper.map(zone, ZoneInfoDto.class);
			}
		} catch (Exception e) { }
		return null;
	}
	
	@Transactional
	@Override
	public ZoneInfoDto renameZone(ZoneInfoDto zoneInfoDto) {
		try {
			if(zoneRepo.existsById(zoneInfoDto.getIdZone())) {
				zoneRepo.updateZoneName(zoneInfoDto.getName(), zoneInfoDto.getIdZone());
				return zoneInfoDto;
			}
		} catch (Exception e) { }
		return null;
	}

	@Transactional
	@Override
	public boolean deleteZone(Integer id) {
		try {
			zoneRepo.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
