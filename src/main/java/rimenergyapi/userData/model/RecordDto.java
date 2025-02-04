package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class RecordDto {

	private Integer groupId;
	
	private Timestamp time;
	
	private double sumAct ;
	
	public RecordDto() {}
	
	public RecordDto(Integer groupId, double sumAct, Timestamp time)  {
		this.groupId = groupId;
		this.sumAct = sumAct;
		this.time = time;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public double getSumAct() {
		return sumAct;
	}
	public void setSumAct(double sumAct) {
		this.sumAct = sumAct;
	}
	
	
	
}
