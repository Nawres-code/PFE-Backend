package rimenergyapi.userData.model;

import java.sql.Timestamp;

public class CalTemperatureDto {
	private int lastTemperatue;
	private int lastHumidity;
	private int lastDi0;
	private int lastDi1;
	private int lastDi2;
	private int lastDi3;
	private Timestamp lastTime;
	private int lastBattery;
	private double sondeId;

	public CalTemperatureDto(int lastTemperatue, int lastHumidity, int lastDi0, int lastDi1, int lastDi2, int lastDi3, Timestamp lastTime, int  lastBattery, int installationId, double sondeId) {
		super();
		this.lastTemperatue = lastTemperatue;
		this.lastHumidity = lastHumidity;
		this.lastTime = lastTime;
		this.lastDi0 = 0;
		this.lastDi1 = 0;
		this.lastDi2 = 0;
		this.lastDi3 = 0;
		this.lastBattery = lastBattery;
		this.sondeId = sondeId;
	}
	
	public CalTemperatureDto(int lastTemperatue, int lastHumidity, int lastDi0, int lastDi1, int lastDi2, int lastDi3, Timestamp lastTime, int  lastBattery) {
		super();
		this.lastTemperatue = lastTemperatue;
		this.lastHumidity = lastHumidity;
		this.lastTime = lastTime;
		this.lastDi0 = 0;
		this.lastDi1 = 0;
		this.lastDi2 = 0;
		this.lastDi3 = 0;
		this.lastBattery = lastBattery;
	}
	
	public CalTemperatureDto(int lastTemperatue, int lastHumidity, int lastDi0, int lastDi1, int lastDi2, int lastDi3, Timestamp lastTime) {
		super();
		this.lastTemperatue = lastTemperatue;
		this.lastHumidity = lastHumidity;
		this.lastTime = lastTime;
		this.lastDi0 = 0;
		this.lastDi1 = 0;
		this.lastDi2 = 0;
		this.lastDi3 = 0;
	}
		
	public CalTemperatureDto(int lastTemperatue, int lastHumidity, int lastDi0, Timestamp lastTime) {
		super();
		this.lastTemperatue = lastTemperatue;
		this.lastHumidity = lastHumidity;
		this.lastTime = lastTime;
		this.lastDi0 = 0;
	}
	
	public CalTemperatureDto() {
		this.lastDi0 = 0;
		this.lastDi1 = 0;
		this.lastDi2 = 0;
		this.lastDi3 = 0;
	}
	
	public CalTemperatureDto(RtSondeTuple tuple) {
		this();
		this.lastBattery = tuple.getLastBattery();
		this.lastHumidity = tuple.getLastHumidity();
		this.lastTemperatue = tuple.getLastTemperatue();
		this.lastTime = tuple.getLastTime();
		this.sondeId = tuple.getSondeId();
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
	public Timestamp getLastTime() {
		return lastTime;
	}
	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}

	public int getLastDi0() {
		return lastDi0;
	}

	public void setLastDi0(int lastDi0) {
		this.lastDi0 = lastDi0;
	}
	
	public int getLastDi1() {
		return lastDi1;
	}
	
	public void setLastDi1(int lastDi1) {
		this.lastDi1 = lastDi1;
	}
	
	public int getLastDi2() {
		return lastDi2;
	}
	
	public void setLastDi2(int lastDi2) {
		this.lastDi2 = lastDi2;
	}
	
	public int getLastDi3() {
		return lastDi3;
	}
	
	public void setLastDi3(int lastDi3) {
		this.lastDi3 = lastDi3;
	}
	public int getLastBattery() {
		return lastBattery;
	}
	public void setLastBattery(int lastBattery) {
		this.lastBattery = lastBattery;
	}
	public double getSondeId() {
		return sondeId;
	}
	public void setSondeId(double sondeId) {
		this.sondeId = sondeId;
	}
}
