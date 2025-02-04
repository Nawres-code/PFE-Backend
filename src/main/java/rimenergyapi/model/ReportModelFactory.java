package rimenergyapi.model;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import rimenergyapi.dto.GroupReportCellDto;
import rimenergyapi.dto.InstallationReportCellDto;

@Component
public class ReportModelFactory {

	@Autowired
	@Qualifier("installationReportModel")
	private ReportModel<Timestamp, GroupReportCellDto> installationReport;

	@Autowired
	@Qualifier("globalReportModel")
	private ReportModel<Long, InstallationReportCellDto> globalreport;
	
	@Autowired
	@Qualifier("hebdoInstallationReportModel")
	private ReportModel<Timestamp, GroupReportCellDto> hebdoInstallationReport;

	public ReportModel getReportModel(String modelType) {
		switch (modelType) {
		case "single_installation": {
			return installationReport;
		}
		case "comparasion":{
			return hebdoInstallationReport;
		}
		case "by_zone": {
			return globalreport;
		}
		}
		return null;
	}
}
