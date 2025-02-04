package rimenergyapi.userData.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rimenergyapi.dto.SensorValuesDto;
import rimenergyapi.service.ArchiveMetosService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/archiveMetos")
@CrossOrigin
@ApiIgnore
@Api(value = "archiveMetos")
public class ArchiveMetosContoller {
	
	@Autowired
	ArchiveMetosService archiveMetosService;

	
	@RequestMapping(value="/stations/{station-id}/sensors", method = RequestMethod.POST)
	public HashMap<Integer, HashMap<String, Map<String, TreeSet<SensorValuesDto>>>> getAllArchiveMetosByStation
				(@RequestBody List<String> sensorIds, @PathVariable ("station-id") String stationId
							, @RequestParam("startTime")  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date startTime
							, @RequestParam("endTime")  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") Date endTime) {
		return archiveMetosService.findSensorsHistoryByStationId
				(stationId, sensorIds, new Timestamp(startTime.getTime()), new Timestamp(endTime.getTime()));
	}
}
