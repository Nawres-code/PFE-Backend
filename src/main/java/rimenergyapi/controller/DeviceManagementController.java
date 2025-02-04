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
import rimenergyapi.dto.DeviceInfoDto;
import rimenergyapi.service.DeviceManagementService;

@RestController
@RequestMapping("/devices")
@CrossOrigin
@Api(value = "device")
public class DeviceManagementController {

	@Autowired
	private DeviceManagementService deviceMngService;
	
	@RequestMapping(value="/{tenantId}", method = RequestMethod.GET)
	public @ResponseBody List<DeviceInfoDto> getAllByTenantId(@PathVariable("tenantId") int tenantId) {
		return deviceMngService.getAllByTenantId(tenantId);
	}
	
	@RequestMapping(value="/device", method = RequestMethod.POST)
	public @ResponseBody DeviceInfoDto createDevice(@RequestBody DeviceInfoDto deviceDto) {
		return deviceMngService.createDevice(deviceDto);
	}
	
	@RequestMapping(value = "/device/{id}", method = RequestMethod.PUT)
	public DeviceInfoDto updateDevice(@PathVariable("id") int id, @RequestBody DeviceInfoDto deviceDto) {
		deviceDto.setId(id);
		return deviceMngService.updateDevice(deviceDto);
	}
	
	@RequestMapping(value = "/device/{id}", method = RequestMethod.DELETE)
	public boolean deleteDevice(@PathVariable("id") int id) {
		return deviceMngService.deleteDevice(id);
	}
	
}
