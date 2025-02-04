package rimenergyapi.userData.controller;

import java.math.BigInteger;
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
import rimenergyapi.service.SondeService;
import rimenergyapi.userData.model.ChartDetailsDto;
import rimenergyapi.userData.model.ChartGazDto;
import rimenergyapi.userData.model.ChartInputDto;
import rimenergyapi.userData.model.ChartPayloadDto;
import rimenergyapi.userData.model.ChartPointDto;
import rimenergyapi.userData.model.ChartSensorDto;
import rimenergyapi.userData.model.ChartStationDto;
import rimenergyapi.userData.model.ChartTemperatureDto;
import rimenergyapi.userData.model.DetailsDto;
import rimenergyapi.userData.model.DetailsGazDto;
import rimenergyapi.userData.model.DetailsPointDto;
import rimenergyapi.userData.model.DetailsSensorsDto;
import rimenergyapi.userData.model.DetailsStationsDto;
import rimenergyapi.userData.model.DetailsTemperateurDto;
import rimenergyapi.userData.service.DetailsService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/details")
@CrossOrigin
@Api(value = "details")
public class DetailsController {

	@Autowired
	DetailsService detailsService;

	@Autowired
	SondeService sondeService;

	@RequestMapping(value="/phases/{period}", method = RequestMethod.POST)
	public Map<Integer, DetailsDto> getHistoricalDetails(@RequestBody ChartDetailsDto data,
														 @ApiParam(value = "Hours|5min|Day|Month", required = true) @PathVariable("period") String period){
		return detailsService.getHistoricalDetails(data.getPhaseIds(), data.getVars(), data.getStartDate(), data.getEndDate(), period);
	}

	@RequestMapping(value="/groups/{period}", method = RequestMethod.POST)
	public Map<Integer, DetailsDto> getHistoricalDetailsByGroup (@RequestBody ChartDetailsDto data,
																 @ApiParam(value = "Hours|5min|Day|Month", required = true) @PathVariable("period") String period) {
		return detailsService.getHistoricalDetailsByGroup(data.getPhaseIds(), data.getVars(), data.getStartDate(), data.getEndDate(), period);
	}
	@ApiIgnore
	@RequestMapping(value="/temperatures/{period}", method = RequestMethod.POST)
	public Map<Double, DetailsTemperateurDto> getHistoricalDetailsByTemperature(@RequestBody ChartTemperatureDto data, @PathVariable("period") String period){
		return detailsService.getHistoricalDetailsByTemperature(data.getInputIds(), data.getSondeIds(), data.getVars(), data.getStartDate(), data.getEndDate(), period);
	}
	@ApiIgnore
	@RequestMapping(value="/temperatures30M", method = RequestMethod.POST)
	public Map<Double, DetailsTemperateurDto> getHistoricalDetailsByTemperature30Minute(@RequestBody ChartTemperatureDto data){
		return detailsService.getHistoricalDetailsByTemperature30Minute(data.getSondeIds(), data.getVars(), data.getStartDate(), data.getEndDate());
	}
	@ApiIgnore
	@RequestMapping(value="/gaz", method = RequestMethod.POST)
	public Map<Integer, DetailsGazDto> getHistoricalDetailsByGaz(@RequestBody ChartGazDto data){
		return detailsService.getHistoricalDetailsByGaz(data.getGazIds(), data.getVars(), data.getStartDate(), data.getEndDate());
	}
	@ApiIgnore
	@RequestMapping(value="/points", method = RequestMethod.POST)
	public Map<Integer, DetailsPointDto> getHistoricalDetailsByPoint(@RequestBody ChartPointDto data){
		return detailsService.getHistoricalDetailsByPoint(data.getPointIds(), data.getDeviceIds(), data.getVars(), data.getStartDate(), data.getEndDate());
	}
	@ApiIgnore
	@RequestMapping(value="/stations", method = RequestMethod.POST)
	public Map<String, DetailsStationsDto> getHistoricalDetailsByStation(@RequestBody ChartStationDto data){
		return detailsService.getHistoricalDetailsByStation(data.getStationIds(), data.getVars(), data.getStartDate(), data.getEndDate());
	}
	@ApiIgnore
	@RequestMapping(value="/sensors", method = RequestMethod.POST)
	public Map<String, DetailsSensorsDto> getHistoricalDetailsBySensor(@RequestBody ChartSensorDto data){
		return detailsService.getHistoricalDetailsBySensor(data.getSensorIds(), data.getVars(), data.getStartDate(), data.getEndDate());
	}

	@RequestMapping(value="/ios/{period}", method = RequestMethod.POST)
	public Map<Integer, DetailsDto> getHistoricalDetailsByIo (@RequestBody ChartPayloadDto data,
															  @ApiParam(value = "Hours|5min|Day|Month", required = true) @PathVariable("period") String period) {
		return detailsService.getIoHistoricalDetailsByDateRangeAndPeriod(data.getIds(),  data.getStartDate(), data.getEndDate(), period);
	}

}
