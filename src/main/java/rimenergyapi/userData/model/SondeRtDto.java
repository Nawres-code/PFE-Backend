package rimenergyapi.userData.model;

import java.math.BigInteger;
import java.sql.Timestamp;

public class SondeRtDto {
	private BigInteger id;
	private int lastTemperatue;
	private int lastHumidity;
	private Timestamp lastTime;
	
	public SondeRtDto(BigInteger id, int lastTemperatue, int lastHumidity, Timestamp lastTime) {
		super();
		this.id = id;
		this.lastTemperatue = lastTemperatue;
		this.lastHumidity = lastHumidity;
		this.lastTime = lastTime;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
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
	
	
	
}
