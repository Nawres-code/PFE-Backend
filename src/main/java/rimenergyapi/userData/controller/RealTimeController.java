package rimenergyapi.userData.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rimenergyapi.dto.RealTimePointDto;
import rimenergyapi.dto.SensorValuesDto;
import rimenergyapi.service.impl.DeviceFroidService;
import rimenergyapi.userData.model.DataRtDto;
import rimenergyapi.userData.model.GazDto;
import rimenergyapi.userData.model.IoDataDto;
import rimenergyapi.userData.model.IoRtDto;
import rimenergyapi.userData.service.RealTimeService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/realtime")
@CrossOrigin
@Api(value = "realtime")
public class RealTimeController {
	
	@Autowired
	private RealTimeService realTimeService;
	@Autowired
	private DeviceFroidService deviceFroidService;
	
	@ApiIgnore
	@RequestMapping(value="/getAll/rtCategorie", method = RequestMethod.GET)
	public List<Object[]> getAllEnergyCategorie(){
		return realTimeService.getAllEnergyCategorie();
	}
	@ApiIgnore
	@RequestMapping(value="/getAll/rtInstallation", method = RequestMethod.GET)
	public List<Object[]> getAllEnergyInstallation(){
		return realTimeService.getAllEnergyInstallation();
	}
	@ApiIgnore
	@RequestMapping(value="/getAll/rtZone", method = RequestMethod.GET)
	public List<Object[]> getAllEnergyZone(){
		return realTimeService.getAllEnergyZone();
	}
	
	
	@RequestMapping(value="/getAll/userData", method = RequestMethod.GET)
	public DataRtDto getAllEnergy(){
		return realTimeService.getAllEnergy(false);
	}
	
	//@GetMapping("/rtpoints")
	@ApiIgnore
	@RequestMapping(value="/rtpoints", method = RequestMethod.GET)
	public List<RealTimePointDto> getAllRtPoint(){
		List<RealTimePointDto> result=new ArrayList<RealTimePointDto>();// null;
		deviceFroidService.getAllDevices().forEach(el->{
				result.addAll(realTimeService.findRTPointByDevice(el.getId()));
		});
		return result ;
	}
	@ApiIgnore
	@RequestMapping(value="/getAll/gaz", method= RequestMethod.GET)
	public List<GazDto> getAllGaz(){
		return realTimeService.getAllEnergyGaz();
	}
	@ApiIgnore
	@RequestMapping(value="/getAll/userDataSingleInstallation", method = RequestMethod.GET)
	public DataRtDto getAllEnergySignleInstallation(){
		return realTimeService.getAllEnergy(true);
	}
	@ApiIgnore
	@RequestMapping(value="/getAll/userDataRecapEnergy", method = RequestMethod.GET)
	public DataRtDto getAllRecapEnergy(@RequestParam("startTime") Timestamp startTime, @RequestParam("endTime") Timestamp endTime){
		return realTimeService.getAllRecapEnergy(startTime,endTime);
	}
	@ApiIgnore
	@RequestMapping(value="/getAll/gazdate", method= RequestMethod.GET)
	public List<GazDto> getAllEnergyGazDate(@RequestParam("startTime") Timestamp startTime, @RequestParam("endTime") Timestamp endTime){
		return realTimeService.getAllEnergyGazDate(startTime,endTime);
	}
	@ApiIgnore
	@RequestMapping(value="/getAll/sensor", method= RequestMethod.GET)
	public Map<Integer, Map<String, List<SensorValuesDto>>> getAllRtSensors() { 
		return realTimeService.getAllRtSensors();
	}
	
	@ApiIgnore
	@RequestMapping(value="/getAll/io", method= RequestMethod.GET)
	public Map<Integer, IoRtDto> getAllRtIo(@RequestParam("period") String period){
		return realTimeService.getAllRtIo(period);
	}
	
}
