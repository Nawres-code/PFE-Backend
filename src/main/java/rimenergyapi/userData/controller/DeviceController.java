package rimenergyapi.userData.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rimenergyapi.userData.service.DeviceService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/devices")
@CrossOrigin
@ApiIgnore
@Api(value = "devices")
public class DeviceController {
	@Autowired
	private DeviceService deviceService;
	
	@GetMapping("/disconnected")
	public Map<String, List<Integer>> getAllDisconnectedDevices(){
		return deviceService.getAllDisconnectedDevice();
	}
}
