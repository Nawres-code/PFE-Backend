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
import rimenergyapi.dto.StationInfoDto;
import rimenergyapi.service.StationService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/stations")
@CrossOrigin
@Api(value = "stations")
@ApiIgnore
public class StationController {
	
	@Autowired
	private StationService stationService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<StationInfoDto> updateSonde(@PathVariable("id") String id ,@RequestBody StationInfoDto station) {
		StationInfoDto res = stationService.updateStation(station);
		if(res != null) {
			return new ResponseEntity<StationInfoDto>(res, HttpStatus.OK); 
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
