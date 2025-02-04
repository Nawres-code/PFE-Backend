package rimenergyapi.userData.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rimenergyapi.entity.DeviceFroidEntity;
import rimenergyapi.service.impl.DeviceFroidService;
import springfox.documentation.annotations.ApiIgnore;
@RestController
@RequestMapping("/froid-devices")
@CrossOrigin
@ApiIgnore
@Api(value = "froid-devices")
public class DeviceFroidController {
	@Autowired
	private DeviceFroidService deviceService;
	
	@GetMapping("/")
	List<DeviceFroidEntity> getAllDevices(){
		return deviceService.getAllDevices();
	}
}
