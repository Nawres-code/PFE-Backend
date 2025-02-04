package rimenergyapi.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import rimenergyapi.dto.InstallationReportCellDto;
import rimenergyapi.dto.ReportDto;
import rimenergyapi.entity.Type.ReportType;

@Component("globalReportModel")
public class GlobalReportModel extends ReportModel<Long, InstallationReportCellDto>{
	

	private SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
	public GlobalReportModel() {
		fileName = "rapport_global" + ".xlsx";
	}
	
	@Override
	public XSSFWorkbook generateReportEnergy(ReportDto reportDto, ReportType type) {

		try {
			zoneId=Integer.parseInt(""+reportDto.getReportDto().getIdsRange().get(0));
			List<InstallationReportCellDto> data = reportRepo
					.findEnergyByInstallationRange(reportDto.getReportDto().getIdsRange(), true, reportDto.getDateInterval().getStartDate(), reportDto.getDateInterval().getEndDate());
			headers = new ArrayList<String>(
					data.stream().map(InstallationReportCellDto::getName).collect(Collectors.toSet()));
			int iGeneralHeader = headers.indexOf("General");
			if (iGeneralHeader > 0) {
				headers.remove(iGeneralHeader);
				headers.add(0, "General");
			}		
			headers.add("Divers");
			
			data.addAll(reportRepo.findEnergyByInstallationRange(reportDto.getReportDto().getIdsRange(), false, reportDto.getDateInterval().getStartDate(), reportDto.getDateInterval().getEndDate()));
			if(!data.isEmpty()) {
				workbook = new XSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:template/"+fileName)));
				workbook.setSheetName(0,generateSheetName(reportDto.getDateInterval().getStartDate(), reportDto.getDateInterval().getEndDate()));
				sheet = workbook.getSheetAt(0);
				sheet.getRow(4).getCell(4).setCellValue("Date de création : "+formater.format(new Date()));
				sheet.getRow(5).getCell(1).setCellValue("Periode du rapport :                                    Du "+formater.format(reportDto.getDateInterval().getStartDate())+"                                   Au "+formater.format(reportDto.getDateInterval().getEndDate()));
				setLogo(sheet, 1, 1, 3);
				rows = data.stream().collect(
						Collectors.groupingBy(InstallationReportCellDto::getIdInstallation, TreeMap::new, Collectors.toList()));
				
				insertHeaders(7, 4);
				style= sheet.getRow(9).getCell(3).getCellStyle();
				insertRows(9);
			}
			else {
				workbook= null;
			}
			

		} catch (EncryptedDocumentException | IOException e) {
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

	private void insertHeaders(int iRow, int iCell) {
		XSSFCell cell;
		XSSFRow rowGroupLbl = sheet.getRow(iRow);
		XSSFRow rowStatus = sheet.getRow(iRow + 1);
		style= rowGroupLbl.getCell(4).getCellStyle();
		for (int i = 0; i < headers.size(); i++, iCell = iCell + 2) {
			cell = rowGroupLbl.createCell(iCell);
			cell.setCellValue(headers.get(i));
			cell.setCellStyle(style);
			cell= rowStatus.createCell(iCell);
			cell.setCellValue("Ouvert");
			cell.setCellStyle(style);
			cell= rowStatus.createCell(iCell + 1);
			cell.setCellValue("Fermé");
			cell.setCellStyle(style);
			sheet.addMergedRegionUnsafe(new CellRangeAddress(iRow, iRow, iCell, iCell + 1));
		}
	}

	
	private StringBuffer generateOthersCellFormula(boolean isOpen, int row) {
		row++;
		int startColumnIndex= isOpen? 4 : 5;
		StringBuffer formula= new StringBuffer(getCellName(startColumnIndex)+row+" - (");
		for(int i=1; i<headers.size()-1; i++) {
			if(i!= 1) {
				formula.append("+ ");
			}
			formula.append(getCellName(i*2+startColumnIndex));
			formula.append(row+" ");
		}
		formula.append(")");
		return formula;
	}
	
	private void insertRows( int iRow){
		XSSFCellStyle redBackgroundStyle= (XSSFCellStyle) style.clone();
		redBackgroundStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
		redBackgroundStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		XSSFCellStyle greenBackgroundStyle= (XSSFCellStyle) style.clone();
		greenBackgroundStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		greenBackgroundStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		XSSFCell cell;
		int iCellGroup;
		for (Map.Entry<Long, List<InstallationReportCellDto>> entry : rows.entrySet()) {
			XSSFRow row = sheet.createRow(iRow);
			XSSFRow rowThreshold = sheet.createRow(iRow + 1);
			// Code
			cell = row.createCell(1);
			cell.setCellValue(entry.getValue().get(0).getCode()+ "");
			cell.setCellStyle(style);
			// magasin
			cell = row.createCell(2);
			cell.setCellValue(entry.getValue().get(0).getMagasin());
			sheet.addMergedRegionUnsafe(new CellRangeAddress(iRow, iRow + 1, 1, 1));
			sheet.addMergedRegionUnsafe(new CellRangeAddress(iRow, iRow + 1, 2, 2));
			cell.setCellStyle(style);
			// 'value - seuil' column
			cell=row.createCell(3);
			cell.setCellValue("Valeur");
			cell.setCellStyle(style);
			rowThreshold = sheet.createRow(iRow + 1);
			cell=rowThreshold.createCell(3);
			cell.setCellValue("Seuil");
			cell.setCellStyle(style);
			// requested groups
			for (InstallationReportCellDto reportCol : entry.getValue()) {
				iCellGroup = headers.indexOf(reportCol.getName()) * 2 + 4;
				iCellGroup = reportCol.getIsOpen() ? iCellGroup : iCellGroup + 1;
				cell=row.createCell(iCellGroup);
				cell.setCellValue(reportCol.getValue());
				if(reportCol.getValue()> reportCol.getThreshold())
				{
					cell.setCellStyle(redBackgroundStyle);
				} else {
					cell.setCellStyle(greenBackgroundStyle);
				}
				cell=rowThreshold.createCell(iCellGroup);
				cell.setCellValue(reportCol.getThreshold());
				cell.setCellStyle(style);
			}
			// 'others' column
			if(row.getCell(4)!=null) {
				iCellGroup = headers.indexOf("Divers") * 2 + 4;
				cell=row.createCell(iCellGroup);
				cell.setCellType(CellType.FORMULA);
				cell.setCellFormula(generateOthersCellFormula(true, iRow).toString());
				cell.setCellStyle(style);
				cell=row.createCell(iCellGroup+1);
				cell.setCellType(CellType.FORMULA);
				cell.setCellFormula(generateOthersCellFormula(false, iRow).toString());
				cell.setCellStyle(style);
				cell=rowThreshold.createCell(iCellGroup);
				cell.setCellType(CellType.FORMULA);
				cell.setCellFormula(generateOthersCellFormula(true, iRow+1).toString());
				cell.setCellStyle(style);
				cell=rowThreshold.createCell(iCellGroup+1);
				cell.setCellType(CellType.FORMULA);
				cell.setCellFormula(generateOthersCellFormula(false, iRow+1).toString());
				cell.setCellStyle(style);
			}
			
			iRow += 2;
		}
	}
	private String generateSheetName(Timestamp startDate, Timestamp endDate) {
		SimpleDateFormat formater= new SimpleDateFormat("dd-MM");
		return formater.format(startDate)+" ~ "+formater.format(endDate);
	}

}
