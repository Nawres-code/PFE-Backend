package rimenergyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rimenergyapi.service.IoImpulseService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/io-impulses")
@CrossOrigin
@ApiIgnore
@Api(value = "io-impulses")
public class IoImpulseController {

	@Autowired
	private IoImpulseService ioImpulseService;
	
	@RequestMapping(value = "/{id}/rename/{name}", method = RequestMethod.GET)
	public void renameIoImpulse(@PathVariable("id") int id, @PathVariable("name") String name) {
		ioImpulseService.renameIoImpulse(id, name);
	}
	
}
