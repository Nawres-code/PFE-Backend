package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class RtPointTuple {

	private int zoneId; 
	private int installationId;
	private int pointId;
	private Double value;
	private Double setpointValue;
	private Timestamp lastTime;
	private int deviceId;
	
	
	public int getInstallationId() {
		return installationId;
	}
	public void setInstallationId(int installationId) {
		this.installationId = installationId;
	}
	public int getPointId() {
		return pointId;
	}
	public void setPointId(int pointId) {
		this.pointId = pointId;
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
	public int getZoneId() {
		return zoneId;
	}
	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}
	
	
}
