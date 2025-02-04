package rimenergyapi.userData.controller;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rimenergyapi.userData.service.RtStatisticalService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/realtime")
@CrossOrigin
@ApiIgnore
@Api(value = "realtime")
public class RtStatisticalController {

	@Autowired
	RtStatisticalService rtStatisticalService;
	
	@RequestMapping(value="/getAll/rtStatistical", method = RequestMethod.GET)
	public Map<Integer, Map<String, Double>> getRtStatistical(@RequestParam ("grouping") String grouping, @RequestParam("timing") String timing ) {
		return rtStatisticalService.getRtStatistical(grouping, timing, "Africa/Tunis");
	}
}
