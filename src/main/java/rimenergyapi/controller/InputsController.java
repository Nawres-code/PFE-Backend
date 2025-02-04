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
import rimenergyapi.dto.InputCategoryInfoDto;
import rimenergyapi.dto.InputInfoDto;
import rimenergyapi.service.InputsService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/inputs")
@CrossOrigin
@ApiIgnore
@Api(value = "inputs")
public class InputsController {
	
	@Autowired
	private InputsService inputsService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<InputInfoDto> updateInput(@PathVariable("id") String id ,@RequestBody InputInfoDto input) {
		InputInfoDto res = inputsService.updateInputName(input);
		if(res != null) {
			return new ResponseEntity<InputInfoDto>(res, HttpStatus.OK); 
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@RequestMapping(value = "/categories/{id}", method = RequestMethod.PUT)
	public ResponseEntity<InputCategoryInfoDto> updateCategoryInput(@PathVariable("id") String id ,@RequestBody InputCategoryInfoDto  category) {
		InputCategoryInfoDto res = inputsService.updateCatInputName(category);
		if(res != null) {
			return new ResponseEntity<InputCategoryInfoDto>(res, HttpStatus.OK); 
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
}
