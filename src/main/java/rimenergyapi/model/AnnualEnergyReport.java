package rimenergyapi.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;


import rimenergyapi.dto.AnnualReportDto;
import rimenergyapi.repository.ReportRepository;
import rimenergyapi.repository.ZonesRepository;

@Service
public class AnnualEnergyReport {

	private CellStyle dateCellStyle;
	private ZoneId defaultZoneId = ZoneId.systemDefault();

	@Autowired
	private ReportRepository reportRepo;
	
	@Autowired
	private ZonesRepository zoneRepo;
	
	@Autowired
	private CurrentTenantIdentifierResolver tenantIdentifier;

	public XSSFWorkbook generateReportEnergy(Timestamp startDate, Timestamp endDate) {
		XSSFWorkbook workbook = null;
		int indexRow = 0;
		Set<Timestamp> headers = null;

		try {
			workbook = new XSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:template/blanc.xlsx")));
			CreationHelper createHelper = workbook.getCreationHelper();
			CellStyle dateCellStyle = workbook.getSheetAt(0).getRow(0).getCell(3).getCellStyle();
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MMM-YY"));
			XSSFSheet sheet = workbook.getSheetAt(0);

			List<AnnualReportDto> data = reportRepo.findAnnualGeneralEnergyPerMonthByHourRange(startDate, endDate);
			headers = data.stream().map(dto -> {
				Timestamp time = dto.getDate();
				time.setDate(1);
				return time;
			}).collect(Collectors.toCollection(TreeSet::new));

			// header
			XSSFRow row = sheet.getRow(0);
			XSSFCell cell;
			int i = 2;
			for (Timestamp date : headers) {
				cell = row.createCell(i);
				cell.setCellValue(date);
				cell.setCellStyle(dateCellStyle);
				i++;
			}
			List<String> ranges = Arrays.asList("7 - 18", "18 - 21", "21 - 7", "6:30 - 8:30", "8:30 - 13:30",
					"13:30 - 19", "19 - 22", "22 - 06:30");
			// rows
			// reportRepo.
			Map<String, Map<Timestamp, Double>> map = data.stream()
					.collect(Collectors.groupingBy(AnnualReportDto::getTimeRange, HashMap::new,
							Collectors.toMap(AnnualReportDto::getDate, AnnualReportDto::getEnergy)));

			Map<Timestamp, Double> ligne = null;
			// First table from 2
			indexRow = 1;
			int indexColumn;
			for (String range : ranges) {
				indexColumn = 1;
				row = sheet.getRow(indexRow);
				// cell = row.getCell(indexColumn);
				// cell.setCellValue(range);
				ligne = map.get(range);
				if (ligne != null) {
					for (Timestamp date : headers) {
						indexColumn++;
						cell = row.getCell(indexColumn);
						if (ligne.containsKey(date))
							cell.setCellValue(ligne.get(date));
					}
				}
				indexRow++;
			}

			// Two table from 2
			indexRow += 4;
			row = sheet.getRow(indexRow);
			i = 2;
			for (Timestamp date : headers) {

				cell = row.getCell(i);
				cell.setCellValue(date);
				cell.setCellStyle(dateCellStyle);
				// cell.setCellStyle(dateCellStyle);
				i++;
			}

			indexRow++;

			for (i = 7; i < 18; i++) {
				indexColumn = 1;
				row = sheet.getRow(indexRow);
				cell = row.getCell(indexColumn);
				// cell.setCellValue(""+i+"_"+(i+1));
				ligne = map.get("hours_" + i);
				if (ligne != null) {
					for (Timestamp date : headers) {
						indexColumn++;
						cell = row.getCell(indexColumn);
						if (ligne.containsKey(date))
							cell.setCellValue(ligne.get(date));
					}
				}
				indexRow++;
			}
			indexRow++;
			for (i = 18; i < 21; i++) {
				indexColumn = 1;
				row = sheet.getRow(indexRow);
				cell = row.getCell(indexColumn);
				// cell.setCellValue(""+i+"_"+(i%24+1));
				ligne = map.get("hours_" + i);
				if (ligne != null) {
					for (Timestamp date : headers) {
						indexColumn++;
						cell = row.getCell(indexColumn);
						if (ligne.containsKey(date))
							cell.setCellValue(ligne.get(date));
					}
				}
				indexRow++;
			}
			indexRow++;
			for (i = 21; i < 31; i++) {
				indexColumn = 1;
				row = sheet.getRow(indexRow);
				cell = row.getCell(indexColumn);
				// cell.setCellValue(""+i%24+"_"+(i%24+1));
				ligne = map.get("hours_" + i % 24);

				if (ligne != null) {
					for (Timestamp date : headers) {
						indexColumn++;
						cell = row.getCell(indexColumn);
						if (ligne.containsKey(date))
							cell.setCellValue(ligne.get(date));
					}
				}
				indexRow++;
			}

			// Three table from 2
			indexRow += 3;
			row = sheet.getRow(indexRow);
			i = 2;
			for (Timestamp date : headers) {

				cell = row.getCell(i);
				cell.setCellValue(date);
				cell.setCellStyle(dateCellStyle);
				// cell.setCellStyle(dateCellStyle);
				i++;
			}

			indexRow++;
			for (i = 7; i < 18; i++) {
				indexColumn = 1;
				row = sheet.getRow(indexRow);
				cell = row.getCell(indexColumn);
				// cell.setCellValue(""+i+"_"+(i+1));
				ligne = map.get("hours1_" + i);
				if (ligne != null) {
					for (Timestamp date : headers) {
						indexColumn++;
						cell = row.getCell(indexColumn);
						if (ligne.containsKey(date))
							cell.setCellValue(ligne.get(date));
					}
				}
				indexRow++;
			}
			indexRow++;
			for (i = 18; i < 21; i++) {
				indexColumn = 1;
				row = sheet.getRow(indexRow);
				cell = row.getCell(indexColumn);
				// cell.setCellValue(""+i+"_"+(i%24+1));
				ligne = map.get("hours1_" + i);
				if (ligne != null) {
					for (Timestamp date : headers) {
						indexColumn++;
						cell = row.getCell(indexColumn);
						if (ligne.containsKey(date))
							cell.setCellValue(ligne.get(date));
					}
				}
				indexRow++;
			}
			indexRow++;
			for (i = 21; i < 31; i++) {
				indexColumn = 1;
				row = sheet.getRow(indexRow);
				cell = row.getCell(indexColumn);
				// cell.setCellValue(""+i%24+"_"+(i%24+1));
				ligne = map.get("hours1_" + i % 24);

				if (ligne != null) {
					for (Timestamp date : headers) {
						indexColumn++;
						cell = row.getCell(indexColumn);
						if (ligne.containsKey(date))
							cell.setCellValue(ligne.get(date));
					}
				}
				indexRow++;
			}

			/*
			 * Four table from 2 indexRow += 3; row = sheet.getRow(indexRow); i = 2; for
			 * (Timestamp date : headers) {
			 * 
			 * cell = row.getCell(i); cell.setCellValue(date);
			 * cell.setCellStyle(dateCellStyle); // cell.setCellStyle(dateCellStyle); i++; }
			 * 
			 * indexRow++; for (i = 7; i < 18; i++) { indexColumn = 1; row =
			 * sheet.getRow(indexRow); cell = row.getCell(indexColumn); //
			 * cell.setCellValue(""+i+"_"+(i+1)); ligne = map.get("hours2_" + i); if (ligne
			 * != null) { for (Timestamp date : headers) { indexColumn++; cell =
			 * row.getCell(indexColumn); if (ligne.containsKey(date))
			 * cell.setCellValue(ligne.get(date)); } } indexRow++; } indexRow++; for (i =
			 * 18; i < 21; i++) { indexColumn = 1; row = sheet.getRow(indexRow); cell =
			 * row.getCell(indexColumn); // cell.setCellValue(""+i+"_"+(i%24+1)); ligne =
			 * map.get("hours2_" + i); if (ligne != null) { for (Timestamp date : headers) {
			 * indexColumn++; cell = row.getCell(indexColumn); if (ligne.containsKey(date))
			 * cell.setCellValue(ligne.get(date)); } } indexRow++; } indexRow++; for (i =
			 * 21; i < 31; i++) { indexColumn = 1; row = sheet.getRow(indexRow); cell =
			 * row.getCell(indexColumn); // cell.setCellValue(""+i%24+"_"+(i%24+1)); ligne =
			 * map.get("hours2_" + i % 24);
			 * 
			 * if (ligne != null) { for (Timestamp date : headers) { indexColumn++; cell =
			 * row.getCell(indexColumn); if (ligne.containsKey(date))
			 * cell.setCellValue(ligne.get(date)); } } indexRow++; }
			 */

			HSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workbook;
	}


	public XSSFWorkbook generateReportEnergyByInstallation(Timestamp startDate, Timestamp endDate) {
		XSSFWorkbook workbook = null;
		int indexRow = 0 ;
		Set<Timestamp> headers = null;
		Set<String> ranges = null;
		Set<String> installations = null;
		XSSFCellStyle totalStyle = null;
		List<AnnualReportDto> data = null;
		SimpleDateFormat formater = new SimpleDateFormat();
		
		try {
			data = reportRepo.findAnnualGeneralEnergyPerMonthByInstallation(startDate, endDate);
			if( data == null || data.isEmpty()) {
				throw new Exception("No data to display!");
			}

			workbook = new XSSFWorkbook(new FileInputStream(ResourceUtils.getFile("classpath:template/unileverPerMonth.xlsx")));
			CreationHelper createHelper = workbook.getCreationHelper();
			
			XSSFFont fontHeader = workbook.createFont();
			fontHeader.setFontHeightInPoints((short)14);
			fontHeader.setFontName("Calibri");
			fontHeader.setColor(new XSSFColor(new java.awt.Color(255, 255, 255)));
			
			
			XSSFFont font = workbook.createFont();
			font.setFontHeightInPoints((short)14);
			font.setFontName("Calibri");
			
			XSSFFont fontValues = workbook.createFont();
			fontValues.setFontHeightInPoints((short)11);
			fontValues.setFontName("Calibri");
		    
			XSSFCellStyle globalStyle = workbook.createCellStyle();
			globalStyle.setFont(fontValues);
			globalStyle.setAlignment(CellStyle.ALIGN_CENTER);
			globalStyle.setBorderBottom(BorderStyle.THIN);
			globalStyle.setBorderLeft(BorderStyle.THIN);
			globalStyle.setBorderRight(BorderStyle.THIN);
			globalStyle.setBorderTop(BorderStyle.THIN);
			globalStyle.setWrapText(true);
			
			XSSFCellStyle headerCellStyle =  workbook.createCellStyle();
			headerCellStyle.cloneStyleFrom(globalStyle);
			headerCellStyle.setFont(fontHeader);
			headerCellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(42, 96, 153)));
			headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			headerCellStyle.setWrapText(true);
			
			XSSFCellStyle dateCellStyle =  workbook.createCellStyle();
			dateCellStyle.cloneStyleFrom(headerCellStyle);
			dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MMM-YY"));
			dateCellStyle.setWrapText(true);
			
			totalStyle = workbook.createCellStyle();
			totalStyle.cloneStyleFrom(globalStyle);
			totalStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(221, 221, 221)));
			totalStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			totalStyle.setFont(font);
			totalStyle.setWrapText(true);

			XSSFSheet sheet = workbook.getSheetAt(0);
			sheet.setDefaultRowHeightInPoints(20);
			
			headers = data.stream().map(AnnualReportDto::getDate).collect(Collectors.toCollection(TreeSet::new));
			ranges = data.stream().map(AnnualReportDto::getTimeRange).collect(Collectors.toCollection(TreeSet::new));
			installations = ranges.stream().map(range -> range.split("-")[0]).collect(Collectors.toCollection(TreeSet::new));
		
			// header
			XSSFRow row = sheet.createRow(10);
			XSSFCell cell;
			int i = 5;
			for (Timestamp date :headers) {
				cell = row.createCell(i);
				cell.setCellValue(date);
				cell.setCellStyle(dateCellStyle);
				i++;
			}
			 
			// rows
			//reportRepo.
			Map<String, Map<Timestamp, Double>> map = data.stream()
					.collect(Collectors.groupingBy(AnnualReportDto::getTimeRange, HashMap::new,
					Collectors.toMap(AnnualReportDto::getDate, AnnualReportDto::getEnergy)));	
			
			Map<Timestamp, Double> ligne = null;
			indexRow = 11;
			int indexColumn = 1;
			int installationStartIndex;
			int installationEndIndex;
			Set<String> sousRange = null;
			
			for (String installation : installations) {
				sousRange = ranges.stream().filter(rangeStr->rangeStr.startsWith(installation)).collect(Collectors.toCollection(TreeSet::new));
				installationStartIndex = indexRow + 1;
				installationEndIndex = indexRow + sousRange.size();
				for(String range : sousRange) {
					indexColumn = 1;
					row = sheet.createRow(indexRow);
					cell = row.createCell(indexColumn);
					cell.setCellValue(range);
					cell.setCellStyle(headerCellStyle);
					sheet.addMergedRegionUnsafe(new CellRangeAddress(indexRow, indexRow, indexColumn, indexColumn + 3));
					indexColumn = 4;
					ligne = map.get(range);
					if(ligne != null) {
						for(Timestamp date :headers) {
							indexColumn++;
							cell = row.createCell(indexColumn);
							if(ligne.containsKey(date)) {
								cell.setCellValue(ligne.get(date));
								cell.setCellStyle(globalStyle);
							}
						}
					}
					indexRow++;
				}
				// totale
				if(indexRow == installationEndIndex) {
					indexColumn = 1;
					row = sheet.createRow(indexRow);
					cell = row.createCell(indexColumn);
					cell.setCellStyle(totalStyle);
					cell.setCellValue("Total "+ installation);
					sheet.addMergedRegionUnsafe(new CellRangeAddress(indexRow, indexRow, indexColumn, indexColumn + 3));
					indexColumn = 5;
					for( i = indexColumn; i< headers.size()+5; i++) {
						cell = row.createCell(i);
						cell.setCellStyle(totalStyle);
						cell.setCellType(CellType.FORMULA);	
						String columnName = cell.getAddress().formatAsString().replaceAll("\\d+", "");
						cell.setCellFormula(String.format("SUM("+columnName+ installationStartIndex + ":" + columnName + installationEndIndex) + ")"); 
					 }
				}
			}
			
			// l'en-tete 
			
			formater.applyPattern("dd-MM-yyyy hh:mm");
			sheet.getRow(4).getCell(5).setCellValue("Date de création: "+ formater.format(new Date()));
			formater.applyPattern("dd-MMM-YY");
			sheet.getRow(6).getCell(5).setCellValue("Période du rapport :     Du " + formater.format(startDate) + "      Au " + formater.format(endDate));
			setLogo(workbook,sheet, 2, 2, 6, 4);
			
			HSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return workbook;
	}
	
	private void setLogo(XSSFWorkbook workbook, XSSFSheet sheet, int row, int col, int rowEnd, int colEnd) {
		FileInputStream my_banner_image;
		try {
			String logo = zoneRepo.getZoneByTenantId(Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier())).get(0).getType();
			my_banner_image = new FileInputStream(ResourceUtils.getFile("classpath:template/"+logo+".png"));
			/* Convert picture to be added into a byte array */
			byte[] bytes = IOUtils.toByteArray(my_banner_image);
		    int myPictureId = workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);
		    XSSFDrawing drawing = sheet.createDrawingPatriarch();
		    XSSFClientAnchor myAnchor = new XSSFClientAnchor();
		    myAnchor.setAnchorType(ClientAnchor.AnchorType.MOVE_AND_RESIZE);
		    myAnchor.setCol1(col);
		    myAnchor.setCol2(colEnd);
		    myAnchor.setRow1(row);
		    myAnchor.setRow2(rowEnd);
		    XSSFPicture myPicture = drawing.createPicture(myAnchor, myPictureId);
		   // myPicture.resize(resize);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private String getCellName(int indexColumn) {
		// need some enhancement
		char alphabet = (char) (65 + indexColumn);
		return alphabet + "";
	}
}
