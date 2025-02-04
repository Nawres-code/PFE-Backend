package rimenergyapi.dto;

public class SensorDto {
	private String id;
	private String groupId;
	private String name;
	private String unit;
	private String aggr;
	private String graphType;
	private String color;
	private String deviceType;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getAggr() {
		return aggr;
	}

	public void setAggr(String aggr) {
		this.aggr = aggr;
	}
	
	public String getGraphType() {
		return graphType;
	}
	
	public void setGraphType(String graphType) {
		this.graphType = graphType;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	
}
