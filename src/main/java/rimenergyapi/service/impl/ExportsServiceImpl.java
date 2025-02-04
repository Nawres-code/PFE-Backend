package rimenergyapi.service.impl;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.dto.ReportDto;
import rimenergyapi.model.AnnualEnergyReport;
import rimenergyapi.model.MonthlyEnergyReport;
import rimenergyapi.model.ReportModelFactory;
import rimenergyapi.service.ExportsService;

@Service
public class ExportsServiceImpl implements ExportsService {
	
	@Autowired
	private ReportModelFactory modelFactory;
	
	@Autowired
	private AnnualEnergyReport annualEnergyReport;
	
	@Autowired
	private MonthlyEnergyReport monthlyEnergyReport;

	@Override
	public XSSFWorkbook generateReportEnergy(ReportDto reportDto, int timeDiff) throws FileNotFoundException {
		reportDto.setTimeDiff(timeDiff);
		return modelFactory.getReportModel(reportDto.getReportDto().getType().getValue())
				.generateReportEnergy(reportDto,reportDto.getReportDto().getType());
	}

	@Override
	public XSSFWorkbook generateAnnualReportEnergy(Timestamp startDate, Timestamp endDate, Optional<String> type) {	
		if(type.isPresent()) {
			switch (type.get().toLowerCase()) {
			case "installation":
				return annualEnergyReport.generateReportEnergyByInstallation(startDate, endDate);
			default:
				return null;
			}
		}
		return annualEnergyReport.generateReportEnergy(startDate, endDate);
	}
	
	@Override
	public XSSFWorkbook generateMonthlyReportEnergy(ReportDto reportDto) {
		switch(reportDto.getType()) {
		case "ISSAT_GROUP":
			return monthlyEnergyReport.generateReportIssatEnergy(reportDto);
		default:
			return monthlyEnergyReport.generateReportIssatEnergy(reportDto);
		}
	}
	
}