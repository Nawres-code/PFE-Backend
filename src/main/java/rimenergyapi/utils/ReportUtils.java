package rimenergyapi.utils;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReportUtils {

	@SuppressWarnings("deprecation")
	public static XSSFCellStyle getCellStyle(XSSFWorkbook workbook) {

		XSSFCellStyle style = workbook.createCellStyle();
		style.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		style.setBorderTop(XSSFCellStyle.BORDER_THIN);
		style.setBorderRight(XSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
		style.setAlignment(CellStyle.ALIGN_LEFT);
		style.setFont(workbook.createFont());

		return style;
	}

	public static XSSFCellStyle cloneStyleFromSpecifiedCell(XSSFWorkbook workbook, int rowNumber) {
		XSSFCellStyle style = workbook.createCellStyle();
		try {
			style.cloneStyleFrom(workbook.getSheetAt(0).getRow(rowNumber).getCell(0).getCellStyle());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR while loading styles....");
		}
		return style;
	}
}
