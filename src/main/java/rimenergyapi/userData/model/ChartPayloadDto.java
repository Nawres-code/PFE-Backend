package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class ChartPayloadDto {
	int[] ids;
	String[] vars; 
	Timestamp startDate,endDate;
	
	public ChartPayloadDto() {}

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
	
	public int[] getIds() {
		return ids;
	}
	
	public void setIds(int[] ids) {
		this.ids = ids;
	}

	public ChartPayloadDto(int[] ids, String[] vars, Timestamp startDate, Timestamp endDate) {
		this.ids = ids;
		this.vars = vars;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
}
