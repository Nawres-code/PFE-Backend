package rimenergyapi.userData.model;

import java.math.BigInteger;
import java.sql.Timestamp;

public class ChartPointDto {
	int[] pointIds;
	int[] deviceIds;
	String[] vars; 
	Timestamp startDate,endDate;
	
	public ChartPointDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ChartPointDto(int[] pointIds,int[] deviceIds, String[] vars, Timestamp startDate, Timestamp endDate) {
		super();
		this.pointIds = pointIds;
		this.deviceIds = deviceIds;
		this.vars = vars;
		this.startDate = startDate;
		this.endDate = endDate;
	}



	public int[] getPointIds() {
		return pointIds;
	}
	public void setPointIds(int[] pointIds) {
		this.pointIds = pointIds;
	}
	
	public int[] getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(int[] deviceIds) {
		this.deviceIds = deviceIds;
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
