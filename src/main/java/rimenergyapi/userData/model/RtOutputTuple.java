package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class RtOutputTuple {

	private int zoneId; 
	private int installationId;
	private int outputId;
	private Boolean isOn;
	private Integer programsId;
	private Timestamp lastTime;

	
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
	public int getOutputId() {
		return outputId;
	}
	public void setOutputId(int outputId) {
		this.outputId = outputId;
	} 	
	public Boolean isOn() {
		return isOn;
	}
	public void setOn(Boolean isOn) {
		this.isOn = isOn;
	}
	public Integer getProgramsId() {
		return programsId;
	}
	public void setProgramsId(Integer programsId) {
		this.programsId = programsId;
	}

	public Timestamp getLastTime() {
		return lastTime;
	}
	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}
	
}
