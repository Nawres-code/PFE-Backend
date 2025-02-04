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
import rimenergyapi.dto.data.InstallationInfoDto;
import rimenergyapi.service.InstallationService;

@RestController
@RequestMapping("/installations")
@CrossOrigin
@Api(value = "installation")
public class InstallationController {

	@Autowired
	private  InstallationService installationService;

	@RequestMapping(value = "/{tenantId}", method = RequestMethod.GET)
	public @ResponseBody List<InstallationInfoDto> getLabels(@PathVariable("tenantId") int tenantId) {
		return installationService.getAllInstallation(tenantId);
	}
	
	@RequestMapping(value = "/installation", method = RequestMethod.POST)
	public InstallationInfoDto addInstallation(@RequestBody InstallationInfoDto installationInfoDto) {
		return installationService.addInstallation(installationInfoDto);
	}
	
	@RequestMapping(value = "/installation/{id}", method = RequestMethod.PUT)
	public InstallationInfoDto updateInstallation(@RequestBody InstallationInfoDto installationInfoDto) {
		return installationService.updateInstallation(installationInfoDto);
	}
	
	@RequestMapping(value = "/installation/{id}", method = RequestMethod.DELETE)
	public boolean deleteInstallation(@PathVariable("id") int id) {
		return installationService.deleteInstallation(id);
	}
	
}
