package rimenergyapi.model;

import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import rimenergyapi.dto.GroupReportCellDto;
import rimenergyapi.dto.ReportDto;
import rimenergyapi.entity.Type.ReportType;

@Component("hebdoInstallationReportModel")
public class HebdoInstallationReportModel extends InstallationReportModel{
	private final String datePattern= "dd-MM-yy";
	private  XSSFSheet openSheet;
	private List<GroupReportCellDto> openData = new ArrayList<>();
	private  XSSFSheet closeSheet;
	private List<GroupReportCellDto> closeData = new ArrayList<>();
	
	public HebdoInstallationReportModel() {
		fileName = "energy_hebdo" + ".xlsx";
		formater.applyPattern(datePattern);
	}
	public XSSFWorkbook generateReportEnergy(ReportDto reportDto, ReportType type) {
		try {
			timeDiff= reportDto.getTimeDiff();
			idInstallation= reportDto.getReportDto().getIdsRange().get(0);
			getInstallation();
			zoneId= Integer.parseInt(installation[2]+"");
			headers= new ArrayList<String>();
			headers.add("Date");
			
			// headers
			headers.addAll(1, reportRepo.findGroupsLabels(reportDto.getReportDto().getIdsRange()));
			headers.add("Others");
			othersCellName = getCellName(headers.size() - 2);
			
			// data
			data= new ArrayList<GroupReportCellDto>();
			closeData= reportRepo.findDailyEnergyByInstallation(reportDto.getReportDto().getIdsRange(), reportDto.getDateInterval().getStartDate(), reportDto.getDateInterval().getEndDate(), false);
			openData= reportRepo.findDailyEnergyByInstallation(reportDto.getReportDto().getIdsRange(), reportDto.getDateInterval().getStartDate(), reportDto.getDateInterval().getEndDate(), true);
			GroupReportCellDto closeCell= null;
			GroupReportCellDto openCell= null;
			GroupReportCellDto groupValue = null;
			for(int i=0; i<closeData.size();i++) {
				try {
				    groupValue= (GroupReportCellDto) closeData.get(i).clone();
					closeCell = closeData.get(i);
					for(GroupReportCellDto op : openData ) {
						if(op.getTime().equals(openCell.getTime())) {
							openCell = op;
							break;
						}
					}
					groupValue.setValue(closeCell.getValue() + openCell.getValue());
				} catch (Exception e) {}
			}
			data.add(groupValue);
			
			if (!data.isEmpty()) {
				workbook = new XSSFWorkbook(
						new FileInputStream(ResourceUtils.getFile("classpath:template/" + fileName)));
				// page 1 modifications
				sheet = workbook.getSheetAt(0);
				setLogo(sheet, 1, 9, 2);
				sheet.getRow(1).getCell(0).setCellValue("Magasin : "+ installation[0]);
				sheet.getRow(2).getCell(0).setCellValue("Code : "+ installation[1]);
				sheet.getRow(3).getCell(0).setCellValue("Rapport hebdomadaire des consommations du "
						+ formater.format(new Timestamp(reportDto.getDateInterval().getStartDate().getTime()+timeDiff)) + " au "+ formater.format(new Timestamp(reportDto.getDateInterval().getEndDate().getTime()+timeDiff))+" pour l'installation ");
				
				openSheet= workbook.getSheetAt(2);
				closeSheet= workbook.getSheetAt(3);
				insert(openSheet, openData);
				insert(closeSheet, closeData);
				sheet = workbook.getSheetAt(1);
				insert(sheet, data);
			} else {
				workbook = null;
			}
			
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}
}
