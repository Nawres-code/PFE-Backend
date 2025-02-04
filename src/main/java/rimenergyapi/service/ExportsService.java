package rimenergyapi.service;

import java.io.FileNotFoundException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Optional;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import rimenergyapi.dto.ReportDto;


public interface ExportsService {

	/** generate energy report **/
	XSSFWorkbook generateReportEnergy(ReportDto reportDto, int timeDiff) throws FileNotFoundException;
	
	/** generate energy report annuel **/
	XSSFWorkbook generateAnnualReportEnergy(Timestamp startDate, Timestamp endDate, Optional<String> type) 
			throws FileNotFoundException;

	XSSFWorkbook generateMonthlyReportEnergy(ReportDto reportDto);

}
