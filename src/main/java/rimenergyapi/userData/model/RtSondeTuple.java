package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class RtSondeTuple {

	private int zoneId;
	private int installationId;
	private double sondeId;
	private Timestamp lastTime;
	private int lastTemperatue;
	private int lastHumidity;
	private int lastBattery;
	
	public RtSondeTuple() {}
	
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
	public double getSondeId() {
		return sondeId;
	}
	public void setSondeId(double sondeId) {
		this.sondeId = sondeId;
	}
	public Timestamp getLastTime() {
		return lastTime;
	}
	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}
	public int getLastTemperatue() {
		return lastTemperatue;
	}
	public void setLastTemperatue(int lastTemperatue) {
		this.lastTemperatue = lastTemperatue;
	}
	public int getLastHumidity() {
		return lastHumidity;
	}
	public void setLastHumidity(int lastHumidity) {
		this.lastHumidity = lastHumidity;
	}
	public int getLastBattery() {
		return lastBattery;
	}
	public void setLastBattery(int lastBattery) {
		this.lastBattery = lastBattery;
	}
	
	
}
