package rimenergyapi.dto;

public class PointInfoDto {
	private int id;
	private String label;
	private String unit;//°C, 
	private int setpointId;
	private int deviceId;
	private String type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getSetpointId() {
		return setpointId;
	}
	public void setSetpointId(int setpointId) {
		this.setpointId = setpointId;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getType() {
		return type;
	}
}
