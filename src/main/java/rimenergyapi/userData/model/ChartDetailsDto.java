package rimenergyapi.userData.model;

import java.sql.Timestamp;
import java.util.List;

public class ChartDetailsDto {
	int[] phaseIds;
	String[] vars; 
	Timestamp startDate,endDate;
	
	public ChartDetailsDto() {}
	public ChartDetailsDto(int[] phaseIds, String[] vars, Timestamp startDate, Timestamp endDate) {
		super();
		this.phaseIds = phaseIds;
		this.vars = vars;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int[] getPhaseIds() {
		return phaseIds;
	}

	public void setPhaseIds(int[] phaseIds) {
		this.phaseIds = phaseIds;
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
