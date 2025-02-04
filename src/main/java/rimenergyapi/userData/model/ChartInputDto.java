package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class ChartInputDto {
	Double[] inputIds;
	Timestamp startDate,endDate;
	
	
	public ChartInputDto() {
		
	}
	public ChartInputDto(Double[] inputIds, Timestamp startDate, Timestamp endDate) {
		this.inputIds = inputIds;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Double[] getInputIds() {
		return inputIds;
	}

	public void setInputIds(Double[] inputIds) {
		this.inputIds = inputIds;
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
