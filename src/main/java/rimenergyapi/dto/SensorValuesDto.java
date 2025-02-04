package rimenergyapi.dto;

import java.sql.Timestamp;

public class SensorValuesDto {
	
	private Timestamp lastTime;
	private String stationId;
	private String sensorId;
	private int installationId;
	private Double avg;
	private Double min;
	private Double max;
	private Double sum;
	private Double last;
	private Double time;
	
	public SensorValuesDto() {
		
	}

	
	public SensorValuesDto(Timestamp lastTime, String stationId, String sensorId, int installationId, Double avg,
			Double min, Double max, Double sum, Double last, Double time) {
		super();
		this.lastTime = lastTime;
		this.stationId = stationId;
		this.sensorId = sensorId;
		this.installationId = installationId;
		this.avg = avg;
		this.min = min;
		this.max = max;
		this.sum = sum;
		this.last = last;
		this.time = time;
	}



	public Timestamp getLastTime() {
		return lastTime;
	}
	public void setLastTime(Timestamp lastTime) {
		this.lastTime = lastTime;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public String getSensorId() {
		return sensorId;
	}
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}
	public Double getAvg() {
		return avg;
	}
	public void setAvg(Double avg) {
		this.avg = avg;
	}
	public Double getMin() {
		return min;
	}
	public void setMin(Double min) {
		this.min = min;
	}
	public Double getMax() {
		return max;
	}
	public void setMax(Double max) {
		this.max = max;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
	public Double getLast() {
		return last;
	}
	public void setLast(Double last) {
		this.last = last;
	}
	public Double getTime() {
		return time;
	}
	public void setTime(Double time) {
		this.time = time;
	}
	public int getInstallationId() {
		return installationId;
	}
	public void setInstallationId(int installationId) {
		this.installationId = installationId;
	}

	@Override
	public String toString() {
		return "SensorValuesDto [lastTime=" + lastTime + ", stationId=" + stationId + ", sensorId=" + sensorId
				+ ", installationId=" + installationId + ", avg=" + avg + ", min=" + min + ", max=" + max + ", sum="
				+ sum + ", last=" + last + ", time=" + time + "]";
	}
	
	
}
