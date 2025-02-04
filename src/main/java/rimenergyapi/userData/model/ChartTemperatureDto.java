package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class ChartTemperatureDto {
	Double[] sondeIds;
	String[] vars; 
	int[] inputIds;
	Timestamp startDate,endDate;
	
	
	public ChartTemperatureDto() {
		
	}
	public ChartTemperatureDto(Double[] sondeIds, String[] vars, Timestamp startDate, Timestamp endDate) {
		this.sondeIds = sondeIds;
		this.vars = vars;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public ChartTemperatureDto(int[] inputIds, Double[] sondeIds, String[] vars, Timestamp startDate, Timestamp endDate) {
		this.inputIds = inputIds;
		this.sondeIds = sondeIds;
		this.vars = vars;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public Double[] getSondeIds() {
		return sondeIds;
	}

	public void setSondeIds(Double[] sondeIds) {
		this.sondeIds = sondeIds;
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
	public int[] getInputIds() {
		return inputIds;
	}
	
	public void setInputIds(int[] inputIds) {
		this.inputIds = inputIds;
	}
	
	
}
