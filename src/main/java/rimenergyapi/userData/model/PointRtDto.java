package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class PointRtDto {

	private int id;
	private int deviceId;
	private double setpointValue;
	private double value;
	private Timestamp lastTime;
	
	public PointRtDto(int id, int deviceId, double setpointValue, double value,Timestamp lastTime) {
		super();
		this.id = id;
		this.deviceId=deviceId;
		this.setpointValue = setpointValue;
		this.value = value;
		this.lastTime = lastTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSetpointValue() {
		return setpointValue;
	}

	public void setSetpointValue(double setpointValue) {
		this.setpointValue = setpointValue;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Timestamp getLastTime() {
		return lastTime;
	}

	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public int getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	
	

	
}
