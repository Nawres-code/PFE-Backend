package rimenergyapi.controller;


import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rimenergyapi.dto.DateIntervalDto;
import rimenergyapi.dto.ReportDto;
import rimenergyapi.service.ExportsService;
import rimenergyapi.utils.Utils;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@CrossOrigin
@ApiIgnore
@Api(value = "exports", description = "Exports Management !")
public class ExportsController {

	@Value("${path-excelReports}")
	private String path;

	@Autowired
	private ExportsService exportsService;

	@RequestMapping(value = "/generateReport", method = RequestMethod.POST)
	@ApiOperation(value = "/generateReport", response = Iterable.class, notes = "Report generation in excel format")
	public  void generateRepport(@RequestBody ReportDto reportDto, @RequestParam int timeDiff,HttpServletResponse response) throws Exception {
		XSSFWorkbook ws = null;
		
		switch (reportDto.getFormat().getValue()) {
		    case "excel":
		    	switch (reportDto.getType()) {
			        case "energy":
			        	ws = exportsService.generateReportEnergy(reportDto,0);
		    	}
		}
			Utils.upResponseXSSF(ws, response);
	}
	
	@RequestMapping(value = "/generateAnnualReport", method = RequestMethod.POST)
	@ApiOperation(value = "/generateAnnualReport", response = Iterable.class, notes = "Report generation in excel format")
	public  void generateRepport(@RequestBody DateIntervalDto timeRange, @RequestParam int timeDiff, @RequestParam Optional<String> type, HttpServletResponse response) throws Exception {
		XSSFWorkbook ws = null;
		 //ws = exportsService.generateAnnualReportEnergy(Timestamp.valueOf("2019-01-01 00:00:00"), Timestamp.valueOf(LocalDateTime.now()), type);
		ws = exportsService.generateAnnualReportEnergy(timeRange.getStartDate(),timeRange.getEndDate(), type);
		Utils.upResponseXSSF(ws, response);
	}
	
	
	@RequestMapping(value = "/generateMonthlyReport", method = RequestMethod.POST)
	@ApiOperation(value = "/generateMonthlyReport", response = Iterable.class, notes = "Report generation in excel format")
	public  void generateMonthlyRepport(@RequestBody ReportDto reportDto,  @RequestParam int timeDiff, HttpServletResponse response) throws Exception {
		XSSFWorkbook ws = null;
		 //ws = exportsService.generateAnnualReportEnergy(Timestamp.valueOf("2019-01-01 00:00:00"), Timestamp.valueOf(LocalDateTime.now()), type);
		ws = exportsService.generateMonthlyReportEnergy(reportDto);
		Utils.upResponseXSSF(ws, response);
	}
	
	
	
}
