package rimenergyapi.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import rimenergyapi.dto.GroupReportCellDto;
import rimenergyapi.dto.ReportDto;
import rimenergyapi.entity.Type.ReportType;

@Component ("installationReportModel")
public class InstallationReportModel extends ReportModel<Timestamp, GroupReportCellDto> {
	protected final int GENERAL_COL_INDEX = 12;
	protected final char GENERAL_COL_NAME = 'M';
	
	protected List<GroupReportCellDto> data = new ArrayList<>();
	protected SimpleDateFormat formater = new SimpleDateFormat();
	private final String hourPattern= "dd-MM-yy HH:mm";
	
	protected XSSFRow row;
	protected XSSFCell cell;
	protected String othersCellName;

	protected int timeDiff;
	protected int idInstallation;
	
	protected Object[] installation;
	
	public InstallationReportModel() {
		fileName = "template_journalier" + ".xlsx";
		formater.applyPattern(hourPattern);
		
	}
	
	@Override
	public XSSFWorkbook generateReportEnergy(ReportDto reportDto, ReportType type) {
		
		try {
			timeDiff= reportDto.getTimeDiff();
			idInstallation= reportDto.getReportDto().getIdsRange().get(0);
			getInstallation();
			zoneId= Integer.parseInt(installation[2]+"");
			headers= new ArrayList<String>();
			headers.add("Heure");
		
			// headers
			headers.addAll(1, reportRepo.findGroupsLabels(reportDto.getReportDto().getIdsRange()));
			headers.add("Others");
			othersCellName = getCellName(headers.size()-2);
			
			// data
			data= reportRepo.findHourlyEnergyByInstallation(reportDto.getReportDto().getIdsRange(), reportDto.getDateInterval().getStartDate(), reportDto.getDateInterval().getEndDate());
			if(!data.isEmpty()) {
				workbook = new XSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:template/"+fileName)));
				
				sheet = workbook.getSheetAt(1);
				insert(sheet, data);
				
				formater.applyPattern("dd-MM-yy");
				sheet = workbook.getSheetAt(0);
				//page 1 modifications
				setLogo(sheet, 1, 9, 2);
				sheet.getRow(1).getCell(0).setCellValue("Magasin : "+installation[0] );
				sheet.getRow(2).getCell(0).setCellValue("Code : "+installation[1]);
				sheet.getRow(3).getCell(0).setCellValue("Rapport journalier des consommations du "+formater.format(new Timestamp(reportDto.getDateInterval().getStartDate().getTime()+timeDiff)) +" pour l'installation ");
			}
			else {
				workbook= null;
			}
		} catch (EncryptedDocumentException  | IOException e) {
			// TODO Auto-generated catch block
			try {
				workbook.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
		 return workbook;
	}
	
	protected void insert(XSSFSheet sheet, List<GroupReportCellDto> data) {
		rows = data.stream().collect(
				Collectors.groupingBy(GroupReportCellDto::getTime,TreeMap::new, Collectors.toList())
		);
		
		// insert headers
		int iRow = 0;
		int iCell = 0;
		row = sheet.createRow(iRow);
		for(; iCell<headers.size(); iCell++) {
			cell = row.createCell(iCell);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(headers.get(iCell));
		}
		
		// insert data
		for(Map.Entry<Timestamp,  List<GroupReportCellDto>> entry: rows.entrySet()) {
			iCell = 0;
			// time cell
			row = sheet.createRow(++iRow);
			cell = row.createCell(iCell);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(formater.format(new Timestamp(entry.getKey().getTime()+timeDiff)));
			// requested groups cell
			for(GroupReportCellDto cellDto: entry.getValue()) {
				iCell = cellDto.getType().equals("General")? GENERAL_COL_INDEX : headers.indexOf(cellDto.getName());
				cell = row.createCell(iCell);
				cell.setCellType(CellType.NUMERIC);		
				cell.setCellValue(cellDto.getValue());
			}
			// 'others' cell ( if General exists)
			if(row.getCell(GENERAL_COL_INDEX)!=null) {
				cell = row.createCell(headers.size()-1);
				cell.setCellType(CellType.FORMULA);	
				cell.setCellFormula(
						String.format("%c%d - SUM(B%d:%s%d)"
						,GENERAL_COL_NAME,iRow+1, iRow+1,othersCellName,iRow+1)
				);
			}
		}
	}

	protected void getInstallation() {
		installation= reportRepo.findInstallationNameAndCodeById(idInstallation);
		
	}
}
