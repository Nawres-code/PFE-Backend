package rimenergyapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rimenergyapi.dto.ProgramDto;
import rimenergyapi.entity.ProgramEntity;
import rimenergyapi.service.ProgramService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/program-management")
@CrossOrigin
@Api(value = "program-management")
@ApiIgnore
public class ProgramController {

	@Autowired
	private ProgramService programService;

	@Autowired
	private ModelMapper mapper;

	@PostMapping(value = "/programs")
	public @ResponseBody ProgramDto addProgram(@RequestBody ProgramDto programDto) {
		ProgramEntity program = mapper.map(programDto, ProgramEntity.class);
		program = programService.saveProgram(program);
		return mapper.map(program, ProgramDto.class);
	}
	
	@PutMapping(value = "/programs/{id-program}")
	public @ResponseBody ProgramDto updateProgram(@RequestBody ProgramDto programDto, @PathVariable("id-program") int programId) {
		ProgramEntity program = mapper.map(programDto, ProgramEntity.class);
		program.setId(programId);
		program = programService.saveProgram(program);
		return mapper.map(program, ProgramDto.class);
	}

	@DeleteMapping(value = "/programs/{id-program}")
	public @ResponseBody void deleteProgram(@PathVariable("id-program") int programId) {
		programService.deleteProgram(programId);
	}
}
