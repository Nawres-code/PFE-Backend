package rimenergyapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rimenergyapi.dto.data.ZoneInfoDto;
import rimenergyapi.service.ZoneService;

@RestController
@RequestMapping("/zones")
@CrossOrigin
@Api(value = "zones")
public class ZoneController {

	@Autowired
	private ZoneService zoneService;
	
	@RequestMapping(value = "/{tenantId}", method = RequestMethod.GET)
	public @ResponseBody List<ZoneInfoDto> getAll(@PathVariable("tenantId") int tenantId) {
		return zoneService.getAllZone(tenantId);
	}
	
	@RequestMapping(value = "/zone", method = RequestMethod.POST)
	public ZoneInfoDto addZone(@RequestBody ZoneInfoDto ZoneInfoDto) {
		return zoneService.addZone(ZoneInfoDto);
	}
	
	@RequestMapping(value = "/zone/{id}", method = RequestMethod.PUT)
	public ZoneInfoDto updateZone(@RequestBody ZoneInfoDto zoneInfoDto, @PathVariable("id") int id) {
		zoneInfoDto.setIdZone(id);
		return zoneService.updateZone(zoneInfoDto);
	}
	
	@RequestMapping(value = "/zone/{id}", method = RequestMethod.DELETE)
	public boolean deleteZone(@PathVariable("id") int id) {
		return zoneService.deleteZone(id);
	}
	
	@RequestMapping(value = "/zone/{id}", method = RequestMethod.GET)
	public @ResponseBody ZoneInfoDto getLabels(@PathVariable("id") int id) {
		return zoneService.getZoneById(id);
	}
	
}
