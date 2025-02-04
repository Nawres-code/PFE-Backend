package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class CalcArchivePointDto {

	private int pointId;
	private int deviceId;
	private Timestamp lastTime;
	private Double value;
	private Double setpointValue;
	
	public CalcArchivePointDto(int pointId, int deviceId, Double value, Double setpointValue, Timestamp lastTime) {
		super();
		this.pointId = pointId;
		this.deviceId = deviceId;
		this.value = value;
		this.setpointValue = setpointValue;
		this.lastTime=lastTime;
	}
	public CalcArchivePointDto() {
		// TODO Auto-generated constructor stub
	}
	public int getPointId() {
		return pointId;
	}
	public void setPointId(int pointId) {
		this.pointId = pointId;
	}
	public Timestamp getLastTime() {
		return lastTime;
	}
	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Double getSetpointValue() {
		return setpointValue;
	}
	public void setSetpointValue(Double setpointValue) {
		this.setpointValue = setpointValue;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	
	
}
