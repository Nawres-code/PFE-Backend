package rimenergyapi.userData.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import rimenergyapi.dto.ChartCmpEnergyDto;
import rimenergyapi.dto.ChartEnergyDto;
import rimenergyapi.userData.model.RecordDto;
import rimenergyapi.userData.model.RtEnergyDto;
import rimenergyapi.userData.service.EnergyService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/energy")
@CrossOrigin
@Api(value = "energy")
public class EnergyController {
	
	@Autowired
	EnergyService energyService;
	@ApiIgnore
	@RequestMapping(value="/getAllEnergy/{installationIds}", method = RequestMethod.POST)
	public List<RtEnergyDto> getAllRtEnergyPhase(@PathVariable ("installationIds") long installationIds){
		return energyService.getAllEnergyInstallations(installationIds);
	}
	@ApiIgnore
	@RequestMapping(value="/getAll/rtengery", method = RequestMethod.GET)
	public List<RtEnergyDto> getAllRTEnergy(){
		return energyService.getAllEnergy();
	}
	
	/*@RequestMapping(value="/getAll/RepEnergy5min", method = RequestMethod.POST)
	public Map<Integer, List<RecordDto>> getAllRepEnergy5min(@RequestBody ChartEnergyDto data){
		return energyService.getAllRepEnergy5min( data.getGroupIds(), data.getStartDate(), data.getEndDate());
	}
	
	@RequestMapping(value="/getAll/RepEnergyDay", method = RequestMethod.POST)
	public Map<Integer, List<RecordDto>> getAllRepEnergyDay(@RequestBody ChartEnergyDto data){
		return energyService.getAllRepEnergyDay( data.getGroupIds(), data.getStartDate(), data.getEndDate());
	}
	
	@RequestMapping(value="/getAll/RepEnergyHours", method = RequestMethod.POST)
	public Map<Integer, List<RecordDto>> getAllRepEnergyHours(@RequestBody ChartEnergyDto data){
		return energyService.getAllRepEnergyHours( data.getGroupIds(), data.getStartDate(), data.getEndDate());
	}
	
	@RequestMapping(value="/getAll/RepEnergyMonth", method = RequestMethod.POST)
	public Map<Integer, List<RecordDto>> getAllRepEnergyMonth(@RequestBody ChartEnergyDto data){
		return energyService.getAllRepEnergyMonth( data.getGroupIds(), data.getStartDate(), data.getEndDate());
	}

*/
	@RequestMapping(value="/{type}/getAll/RepEnergy5min", method = RequestMethod.POST)
	public Map<Integer, List<RecordDto>> getAllRepEnergy5minByType(@RequestBody ChartEnergyDto data,   
	@ApiParam(value = "energy_act|energy_react|energy_fund|energy_app", required = true) @PathVariable ("type") String type) {
		return energyService.getAllRepEnergy5minByType( data.getGroupIds(), data.getStartDate(), data.getEndDate(), type);
	}

	@RequestMapping(value="/{type}/getAll/RepEnergyRow", method = RequestMethod.POST)
	public Map<Integer, List<RecordDto>> getAllEnergyByRow(@RequestBody ChartEnergyDto data,
	@ApiParam(value = "energy_act|energy_react|energy_fund|energy_app", required = true) @PathVariable ("type") String type) {
		return energyService.getAllRepEnergyRowByType( data.getGroupIds(), data.getStartDate(), data.getEndDate(), type);
	}
	
	@RequestMapping(value="/{type}/getAll/RepEnergyDay", method = RequestMethod.POST)
	public Map<Integer, List<RecordDto>> getAllRepEnergyDayByType(@RequestBody ChartEnergyDto data,
	@ApiParam(value = "energy_act|energy_react|energy_fund|energy_app", required = true) @PathVariable ("type") String type){
		return energyService.getAllRepEnergyDayByType( data.getGroupIds(), data.getStartDate(), data.getEndDate(), type);
	}
	
	@RequestMapping(value="/{type}/getAll/RepEnergyHours", method = RequestMethod.POST)
	public Map<Integer, List<RecordDto>> getAllRepEnergyHoursByType(@RequestBody ChartEnergyDto data, 
	@ApiParam(value = "energy_act|energy_react|energy_fund|energy_app", required = true) @PathVariable ("type") String type){
		return energyService.getAllRepEnergyHoursByType( data.getGroupIds(), data.getStartDate(), data.getEndDate(), type);
	}
	
	@RequestMapping(value="/{type}/getAll/RepEnergyMonth", method = RequestMethod.POST)
	public Map<Integer, List<RecordDto>> getAllRepEnergyMonthByType(@RequestBody ChartEnergyDto data, 
	@ApiParam(value = "energy_act|energy_react|energy_fund|energy_app", required = true) @PathVariable ("type") String type){
		return energyService.getAllRepEnergyMonthByType( data.getGroupIds(), data.getStartDate(), data.getEndDate(), type);
	}
	@ApiIgnore
	@RequestMapping(value="/compare/RepEnergy", method = RequestMethod.POST)
	public Map<Integer, Map<Timestamp, Map<String, Double>>> getAllRepEnergy(@RequestBody ChartCmpEnergyDto data){
		data.setStartDate(new Timestamp(1580547600*1000L));
		data.setEndDate(new Timestamp(1580590800*1000L));
		return energyService.getAllRepEnergy(data.getGroupedBy(),data.getIds(),data.getStartDate(), data.getEndDate(),data.getTimeBack(), data.getTimeStep());
	}
}
