package rimenergyapi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rimenergyapi.dto.AuthorityInfoDto;
import rimenergyapi.dto.CategoryInfoDto;
import rimenergyapi.dto.ProgramDto;
import rimenergyapi.dto.SensorDto;
import rimenergyapi.dto.ZonesInfoDto;
import rimenergyapi.dto.data.InstallationInfoDto;
import rimenergyapi.entity.ProgramEntity;
import rimenergyapi.entity.SensorEntity;
import rimenergyapi.service.DataManagmentService;
import rimenergyapi.service.ProgramService;
import rimenergyapi.service.SensorService;

@RestController
@RequestMapping("/dataManagment")
@CrossOrigin
@Api(value = "dataManagment")
public class DataManagmentController {

    @Autowired
    private DataManagmentService dataManagmentService;
    
    @Autowired
    private ProgramService programService;
    
    @Autowired
    private SensorService sensorService;
    
    @Autowired
    private ModelMapper mapper;    
    
    @RequestMapping(value="/getAllZone", method = RequestMethod.GET)
    public @ResponseBody List<ZonesInfoDto> getAllZones(){
        return dataManagmentService.getAll();
    }
    
    @RequestMapping(value="/getAllZone/{tenantId}", method = RequestMethod.GET)
    public @ResponseBody List<ZonesInfoDto> getAllZonesByTenantId(@PathVariable("tenantId") int tenantId){
        return dataManagmentService.getAllZonesByTenantId(tenantId);
    }
    
    @RequestMapping(value="/getAllCategory/{tenantId}", method = RequestMethod.GET)
    public @ResponseBody List<CategoryInfoDto> geAllCategory(@PathVariable("tenantId") int tenantId){
        return dataManagmentService.getAllCategory(tenantId);
    }
    
	@RequestMapping(value = "/programs/{tenantId}", method = RequestMethod.GET)
	public @ResponseBody List<ProgramDto> getAllProgramsByTenantId(@PathVariable("tenantId") int tenantId) {
		List<ProgramDto> programsDto = new ArrayList<ProgramDto>();
		List<ProgramEntity> programs = programService.getAllProgramsByCurrentUser();
		programsDto = programs.stream().map(entity -> mapper.map(entity, ProgramDto.class)).collect(Collectors.toList());
		return programsDto;
	}
	
	@RequestMapping(value = "/getAllSensors/", method = RequestMethod.GET)
	public @ResponseBody List<SensorDto> getAllMetosSensors() {
		
		List<SensorDto> sensorsDto = new ArrayList<SensorDto>();
		List<SensorEntity> sensors = sensorService.getAllSensors();
		sensorsDto = sensors.stream().map(entity -> mapper.map(entity, SensorDto.class)).collect(Collectors.toList());
		return sensorsDto; 
	}
	
    @RequestMapping(value="/getAllAuthority", method = RequestMethod.GET)
    public @ResponseBody List<AuthorityInfoDto> getAllAuthority(){
        return dataManagmentService.getAllAuthority();
    }
    
	@RequestMapping(value = "/allInstallation/{tenantId}", method = RequestMethod.GET)
	public @ResponseBody List<InstallationInfoDto> getLabels(@PathVariable("tenantId") int tenantId) {
		return null ;//installationService.getAllInstallation(tenantId);
	}
    
    
}
