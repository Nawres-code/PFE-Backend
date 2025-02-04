package rimenergyapi.userData.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import rimenergyapi.userData.model.RtTricityDto;
import rimenergyapi.userData.service.TricityService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/tricity")
@CrossOrigin
@ApiIgnore
@Api(value = "tricity")
public class TricityController {
	
	@Autowired
	TricityService tricityService;

	@RequestMapping(value="/getAll/rtTricity", method = RequestMethod.GET)
	public List<RtTricityDto> getAllRtTricity(){
		return tricityService.getAllTricity();
	}
}
