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
import rimenergyapi.dto.data.SondeInfoDto;
import rimenergyapi.service.SondeService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/sonde")
@CrossOrigin
@ApiIgnore
@Api(value = "sonde")
public class SondeController {

	@Autowired
	SondeService sondeService;
	
	@RequestMapping(value = "/allSonde/{tenantId}", method = RequestMethod.GET)
	public @ResponseBody List<SondeInfoDto> getLabels(@PathVariable("tenantId") int tenantId) {
		return sondeService.getAllSonde(tenantId);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public SondeInfoDto addSoonde(@RequestBody SondeInfoDto sondeInfoDto) {
		return sondeService.addSonde(sondeInfoDto);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public SondeInfoDto updateSonde(@RequestBody SondeInfoDto sondeInfoDto) {
		return sondeService.updateSonde(sondeInfoDto);
	}
	
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public boolean deleteSonde(@PathVariable("id") Double id) {
		return sondeService.deleteSonde(id);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody SondeInfoDto getLabels(@PathVariable("id") Double id) {
		return sondeService.getSondeById(id);
	}
}
