package rimenergyapi.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "archive_alert")
public class ArchiveAlertEntity {

	private long id;
	private Timestamp time;
	private double calcVal;
	private String infractedVal;
	private String infractedOperator;
	private String alertType;
	private String measureType;
	private String measureId;
	
	@Id
	@Column(name="alert_id")
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "time")
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	} 
	
	@Column(name = "calculated_value")
	public double getCalcVal() {
		return calcVal;
	}
	public void setCalcVal(double calcVal) {
		this.calcVal = calcVal;
	}
	
		@Column(name = "infracted_value")
	public String getInfractedVal() {
		return infractedVal;
	}
	public void setInfractedVal(String infractedVal) {
		this.infractedVal = infractedVal;
	}
	
	@Column(name = "infracted_operator")
	public String getInfractedOperator() {
		return infractedOperator;
	}
	public void setInfractedOperator(String infractedOperator) {
		this.infractedOperator = infractedOperator;
	}
	
	@Column(name = "alert_type")
	public String getAlertType() {
		return alertType;
	}
	public void setAlertType(String alertType) {
		this.alertType = alertType;
	}
	
	@Column(name = "measure_type")
	public String getMeasureType() {
		return measureType;
	}
	public void setMeasureType(String measureType) {
		this.measureType = measureType;
	}
	
	@Column(name="measure_id")
	public String getMeasureId() {
		return measureId;
	}
	public void setMeasureId(String measureId) {
		this.measureId = measureId;
	}
	
}
