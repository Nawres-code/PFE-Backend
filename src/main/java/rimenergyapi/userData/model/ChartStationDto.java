package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class ChartStationDto {
	String[] stationIds;
	String[] vars; 
	Timestamp startDate,endDate;
	
	public ChartStationDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ChartStationDto(String[] stationIds, String[] vars, Timestamp startDate, Timestamp endDate) {
		super();
		this.stationIds = stationIds;
		this.vars = vars;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String[] getStationIds() {
		return stationIds;
	}

	public void setStationIds(String[] stationIds) {
		this.stationIds = stationIds;
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
