package rimenergyapi.userData.controller;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import rimenergyapi.userData.model.ArchiveDto;
import rimenergyapi.userData.model.ArchiveTemperatureDto;
import rimenergyapi.userData.service.ArchiveService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/archiveTemp")
@CrossOrigin
@ApiIgnore
@Api(value = "archiveTemp")
public class ArchiveContoller {

	@Autowired
	ArchiveService archiveService;
	
	@RequestMapping(value = "/getAllArchiv/{sondeId}", method = RequestMethod.POST)
	  public List<ArchiveTemperatureDto> getAllArchiSonde(@PathVariable ("sondeId") BigInteger sondeId, Timestamp startTime, Timestamp endTime) {
		return archiveService.getAllArchiSonde(sondeId, startTime, endTime) ;
	}
	
	@RequestMapping(value="/getAll/archiveTemperature", method = RequestMethod.GET)
	public List<ArchiveTemperatureDto> getAllArchiveTemperature(){
		return archiveService.getAllArchiveTemperature();
	}
	
	@RequestMapping(value="/getAll/archive", method = RequestMethod.GET)
	public @ResponseBody List<ArchiveDto> getAllArchive(){
		return archiveService.getAllArchive();
	}

}
