package rimenergyapi.userData.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rimenergyapi.dto.ArchiveAlertDto;
import rimenergyapi.userData.service.NotificationService;
import springfox.documentation.annotations.ApiIgnore;
@RestController
@RequestMapping("/notifications")
@CrossOrigin
@ApiIgnore
@Api(value = "notifications")
public class NotificationController {
	@Autowired
	private NotificationService notificationService;
	
	@GetMapping("/Alarm_points")
	public List<String> getFiredAlarmPointList(){
		List<String> list = new ArrayList<String>();
		list = notificationService.getFiredAlarmPointList();
		return list;
	}

	@GetMapping("/{days}/userAlerts")
	public List<ArchiveAlertDto> getUserAlert(@PathVariable ("days") int days){
		return notificationService.getUserAlert(days);
	}
}
