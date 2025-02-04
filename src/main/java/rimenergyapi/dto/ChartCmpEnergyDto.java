package rimenergyapi.dto;

import java.sql.Timestamp;

public class ChartCmpEnergyDto {
	private int[] ids;
	private Timestamp startDate, endDate;
	private String timeBack;
	private String timeStep;
	private String groupedBy;
	
	public int[] getIds() {
		return ids;
	}
	public void setIds(int[] ids) {
		this.ids = ids;
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
	public String getTimeBack() {
		return timeBack;
	}
	public void setTimeBack(String timeBack) {
		this.timeBack = timeBack;
	}
	public String getTimeStep() {
		return timeStep;
	}
	public void setTimeStep(String timeStep) {
		this.timeStep = timeStep;
	}
	public String getGroupedBy() {
		return groupedBy;
	}
	public void setGroupedBy(String groupedBy) {
		this.groupedBy = groupedBy;
	}

}
