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
import rimenergyapi.dto.PhaseInfoDto;
import rimenergyapi.service.PhaseManagementService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/phases")
@CrossOrigin
@Api(value = "phase")
public class PhaseController {

	@Autowired
	private PhaseManagementService phaseMngService;
	
	@RequestMapping(value="/{tenantId}", method = RequestMethod.GET)
	public @ResponseBody List<PhaseInfoDto> getAllByTenantId(@PathVariable("tenantId") int tenantId) {
		return phaseMngService.getAllByTenantId(tenantId);
	}
	
	@RequestMapping(value = "/phase", method = RequestMethod.PUT)
	public PhaseInfoDto updatePhase(@RequestBody PhaseInfoDto phaseDto) {
		return phaseMngService.updatePhase(phaseDto);
	}
	
	@RequestMapping(value = "/phase/{id}", method = RequestMethod.DELETE)
	public boolean deletePhase(@PathVariable("id") int id) {
		return phaseMngService.deletePhase(id);
	}
}
