package rimenergyapi.dto;

import java.sql.Timestamp;

public class ArchiveAlertDto {

	private long id;
	private Timestamp time;
	private Double calcVal;
	private String infractedVal;
	private String infractedOperator;
	private String alertType;
	private String measureType;
	private String measureId;
	private String zoneId;
	private String installationId;
	private String fatherId;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	} 
	public Double getCalcVal() {
		return calcVal;
	}
	public void setCalcVal(Double calcVal) {
		this.calcVal = calcVal;
	}
	public String getInfractedVal() {
		return infractedVal;
	}
	public void setInfractedVal(String infractedVal) {
		this.infractedVal = infractedVal;
	}
	public String getInfractedOperator() {
		return infractedOperator;
	}
	public void setInfractedOperator(String infractedOperator) {
		this.infractedOperator = infractedOperator;
	}
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	public String getMeasureType() {
		return measureType;
	}
	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}
	public String getMeasureId() {
		return measureId;
	}
	public void setMeasureId(String measureId) {
		this.measureId = measureId;
	}
	public String getZoneId() {
		return zoneId;
	}
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	public String getInstallationId() {
		return installationId;
	}
	public void setInstallationId(String installationId) {
		this.installationId = installationId;
	}
	public String getFatherId() {
		return fatherId;
	}
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}
	
	
	
}
