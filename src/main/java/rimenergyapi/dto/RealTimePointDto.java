package rimenergyapi.dto;


public class RealTimePointDto {
	
	private int pointId;
	private String pointLbl;
	private double pointValue;
	private double setpointValue;
	//private String deviceLabel;
	
	public int getPointId() {
		return pointId;
	}
	public void setPointId(int pointId) {
		this.pointId = pointId;
	}
	public String getPointLbl() {
		return pointLbl;
	}
	public void setPointLbl(String pointLbl) {
		this.pointLbl = pointLbl;
	}
	public double getPointValue() {
		return pointValue;
	}
	public void setPointValue(double pointValue) {
		this.pointValue = pointValue;
	}
	public double getSetpointValue() {
		return setpointValue;
	}
	public void setSetpointValue(double setpointValue) {
		this.setpointValue = setpointValue;
	}
	
}
