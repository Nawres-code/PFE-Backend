package rimenergyapi.service;

import java.util.List;

import rimenergyapi.dto.data.ZoneInfoDto;


public interface ZoneService {

	List<ZoneInfoDto> getAllZone(int tenantId);
	ZoneInfoDto getZoneById(int id);
	ZoneInfoDto addZone(ZoneInfoDto zoneInfoDto);
	ZoneInfoDto updateZone(ZoneInfoDto zoneInfoDto) ;
	boolean deleteZone(Integer id) ;
	ZoneInfoDto renameZone(ZoneInfoDto zoneInfoDto);

}
