package rimenergyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rimenergyapi.dto.SensorDto;
import rimenergyapi.service.SensorService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/sensors")
@CrossOrigin
@Api(value = "sensors")
@ApiIgnore
public class SensorController {
	
	@Autowired
	private SensorService sensorService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<SensorDto> updateSonde(@PathVariable("id") String id, @RequestBody SensorDto sensor) {
		SensorDto res = sensorService.updateSensor(sensor);
		if(res != null) {
			return new ResponseEntity<SensorDto>(res, HttpStatus.OK); 
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
