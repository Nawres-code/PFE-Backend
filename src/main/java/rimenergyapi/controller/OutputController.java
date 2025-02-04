package rimenergyapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rimenergyapi.service.InstallationService;
import rimenergyapi.service.OutputMqttService;
import rimenergyapi.service.OutputService;
import rimenergyapi.service.ProgramOutputService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/output-api")
@Api(value = "output-api")
@ApiIgnore
public class OutputController {
	
	@Autowired
	private InstallationService installationService;
	
	@Autowired
	private OutputService outputService;
	
	@Autowired
	private OutputMqttService outputMqttService;
	
	@Autowired
	private ProgramOutputService programOutputService;
	
	@PutMapping(value = "/outputs/{id}")
	@ResponseBody
	public Boolean toggleOutputStatus(@PathVariable int id, @RequestBody boolean oldStatus) {	
		try {
			return outputService.updateOutputStatus(id, oldStatus);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return oldStatus;
	}
	
	@PutMapping(value = "/outputs/installation/{idInstallation}")
	@ResponseBody
	public Boolean toggleAutoMode(@PathVariable int idInstallation, @RequestBody boolean oldAutoMode) {
		return installationService.toggleOutputMode(idInstallation, oldAutoMode);

		/*OutputMqttMessage msg = new OutputMqttMessage();
		msg.setCmd("cmd1");
		msg.setTime(12345678910L);
		msg.setIdOutputDevice(1234);
		if(outputMqttService.publish(msg) == 1) {
			try {
				return installationService.toggleOutputMode(idInstallation, oldAutoMode);
			} catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return oldAutoMode;*/
	}
	
	@PutMapping(value = "/outputs/{id}/programs")
	@ResponseBody
	public List<Integer> UpdateOutputPrograms(@PathVariable int id, @RequestBody List<Integer> programs) {
		try {
			programOutputService.updateOutputPrograms(id, programs);
			return programs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
