package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class RtInputTuple {
	
	private int zoneId; 
	private int installationId;
	private int inputId;
	private Timestamp time;
	private int value;
	
	public RtInputTuple() {}
	
	public RtInputTuple(RtInputTuple copie) {
		this.zoneId = copie.zoneId;
		this.installationId = copie.installationId;
		this.inputId = copie.inputId;
		this.time = copie.time;
		this.value = copie.value;
	}
	
	public int getZoneId() {
		return zoneId;
	}
	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}
	public int getInstallationId() {
		return installationId;
	}
	public void setInstallationId(int installationId) {
		this.installationId = installationId;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public int getInputId() {
		return inputId;
	}
	public void setInputId(int inputId) {
		this.inputId = inputId;
	}
	@Override
	public String toString() {
		return "RtInputTuple [zoneId=" + zoneId + ", installationId=" + installationId + ", inputId=" + inputId
				+ ", time=" + time + ", value=" + value + "]";
	}

}
