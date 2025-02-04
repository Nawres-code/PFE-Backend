package rimenergyapi.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import rimenergyapi.dto.ReportDto;
import rimenergyapi.repository.ZonesRepository;

@Service
public class MonthlyEnergyReport {

	
	@Autowired
	private ZonesRepository zoneRepo;
	
	@Autowired
	private CurrentTenantIdentifierResolver tenantIdentifier;
	
	
	

	public XSSFWorkbook generateReportIssatEnergy(ReportDto reportDto) {
		XSSFWorkbook workbook = null;
		SimpleDateFormat formater = new SimpleDateFormat();
	

		try {
			workbook = new XSSFWorkbook(
					new FileInputStream(ResourceUtils.getFile("classpath:template/unileverPerMonth.xlsx")));
			CreationHelper createHelper = workbook.getCreationHelper();
			
			XSSFSheet sheet = workbook.getSheetAt(0);

			// l'en-tete
			formater.applyPattern("dd-MM-yyyy hh:mm");
			sheet.getRow(4).getCell(5).setCellValue("Date de création: " + formater.format(new Date()));
			formater.applyPattern("dd-MMM-YY");
			sheet.getRow(6).getCell(5).setCellValue(
					"Période du rapport :     Du " + formater.format(reportDto.getDateInterval().getStartDate())
							+ "      Au " + formater.format(reportDto.getDateInterval().getEndDate()));
			setLogo(workbook, sheet, 2, 2, 6, 4);
			HSSFFormulaEvaluator.evaluateAllFormulaCells(workbook);

			return workbook;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
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
	
}
