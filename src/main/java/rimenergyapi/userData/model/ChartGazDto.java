package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class ChartGazDto {
	int[] gazIds;
	String[] vars; 
	Timestamp startDate,endDate;
	
	public ChartGazDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ChartGazDto(int[] gazIds, String[] vars, Timestamp startDate, Timestamp endDate) {
		super();
		this.gazIds = gazIds;
		this.vars = vars;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int[] getGazIds() {
		return gazIds;
	}

	public void setGazIds(int[] gazIds) {
		this.gazIds = gazIds;
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
