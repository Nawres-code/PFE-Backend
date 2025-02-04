package rimenergyapi.dto;

import java.util.ArrayList;
import java.util.List;

public class UserAlertFilterDto {
	
	private List<String> measureIds = new ArrayList<String>();
	private List<String> fatherIds = new ArrayList<String>();
	private String userAlertType;
	private String measureType;
	private Integer zoneId ;
	private Integer installationId ;
	
	public List<String> getMeasureIds() {
		return measureIds;
	}
	public void setMeasureIds(List<String> measureIds) {
		this.measureIds = measureIds;
	}
	public List<String> getFatherIds() {
		return fatherIds;
	}
	public void setFatherIds(List<String> fatherIds) {
		this.fatherIds = fatherIds;
	}
	public String getMeasureType() {
		return measureType;
	}
	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}
	public String getUserAlertType() {
		return userAlertType;
	}
	public void setUserAlertType(String userAlertType) {
		this.userAlertType = userAlertType;
	}
	
	public Integer getInstallationId() {
		return installationId;
	} 
	public void setInstallationId(Integer installationId) {
		this.installationId = installationId;
	}
	
	public Integer getZoneId() {
		return zoneId;
	}
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
}
