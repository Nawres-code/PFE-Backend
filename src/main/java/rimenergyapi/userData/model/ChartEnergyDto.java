package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class ChartEnergyDto {
	int[] groupIds;
	Timestamp startDate,endDate;
	public int[] getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(int[] groupIds) {
		this.groupIds = groupIds;
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
	public ChartEnergyDto(int[] groupIds, Timestamp startDate, Timestamp endDate) {
		super();
		this.groupIds = groupIds;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	
}
