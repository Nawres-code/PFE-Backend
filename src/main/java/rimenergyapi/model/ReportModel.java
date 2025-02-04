package rimenergyapi.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import rimenergyapi.dto.ReportDto;
import rimenergyapi.entity.Type.ReportType;
import rimenergyapi.repository.ReportRepository;
import rimenergyapi.repository.ZonesRepository;

@Component
public abstract class ReportModel<T, E> {

	@Autowired
	protected ReportRepository reportRepo;
	
	@Autowired
	protected ZonesRepository zoneRepo;
	
	protected XSSFWorkbook workbook;
	protected String fileName;
	protected List<String> headers;
	protected Map<T, List<E>> rows= new TreeMap<T, List<E>>();
	protected XSSFSheet sheet;
	protected XSSFCellStyle style;
	protected Integer zoneId;
	
	public abstract XSSFWorkbook generateReportEnergy(ReportDto reportDto, ReportType type);
	
	protected void setLogo(XSSFSheet sheet, int row, int col, double resize) {
		String logo= zoneRepo.findById(zoneId).get().getType();
		FileInputStream my_banner_image;
		try {
			my_banner_image = new FileInputStream(ResourceUtils.getFile("classpath:template/"+logo+".png"));
			/* Convert picture to be added into a byte array */
			byte[] bytes = IOUtils.toByteArray(my_banner_image);
		    int myPictureId = workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);
		    XSSFDrawing drawing = sheet.createDrawingPatriarch();
		    XSSFClientAnchor myAnchor = new XSSFClientAnchor();
		    myAnchor.setCol1(col);
		    myAnchor.setRow1(row);
	//	    myAnchor.setDx1(10);//10.21 "
	//	    myAnchor.setDy1(25);//0.24 "
		    XSSFPicture myPicture = drawing.createPicture(myAnchor, myPictureId);
		    myPicture.resize(resize);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected String getCellName(int position) {
		char alphabet=(char) (65 + position);
		return alphabet+"";
	} 
	
}
