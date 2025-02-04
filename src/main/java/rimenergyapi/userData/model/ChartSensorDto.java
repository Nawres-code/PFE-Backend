package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class ChartSensorDto {
	String[] sensorIds;
	String[] vars; 
	Timestamp startDate,endDate;
	
	public ChartSensorDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ChartSensorDto(String[] sensorIds, String[] vars, Timestamp startDate, Timestamp endDate) {
		super();
		this.sensorIds = sensorIds;
		this.vars = vars;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String[] getSensorIds() {
		return sensorIds;
	}

	public void setSensorIds(String[] sensorIds) {
		this.sensorIds = sensorIds;
	}

	public String[] getVars() {
		return vars;
	}
	public void setVars(String[] vars) {
		this.vars = vars;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getEndDate() {
		return endDate;
	}
	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	
	
}
