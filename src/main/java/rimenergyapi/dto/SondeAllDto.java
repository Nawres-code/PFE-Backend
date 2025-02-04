package rimenergyapi.dto;

public class SondeAllDto {
	private String id;
	private String name;
	private Integer indexHumDevice;
	private String type;
	private Integer minThreshold;
	private Integer maxThreshold;
	
    public SondeAllDto() {
    	super();
    }
    
	public SondeAllDto(String id, String name, Integer indexHumDevice, String type, Integer minThreshold,
			Integer maxThreshold) {
		super();
		this.id = id;
		this.name = name;
		this.indexHumDevice = indexHumDevice;
		this.type = type;
		this.minThreshold = minThreshold;
		this.maxThreshold = maxThreshold;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getIndexHumDevice() {
		return indexHumDevice;
	}
	public void setIndexHumDevice(Integer indexHumDevice) {
		this.indexHumDevice = indexHumDevice;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getMinThreshold() {
		return minThreshold;
	}
	public void setMinThreshold(Integer minThreshold) {
		this.minThreshold = minThreshold;
	}
	public Integer getMaxThreshold() {
		return maxThreshold;
	}
	public void setMaxThreshold(Integer maxThreshold) {
		this.maxThreshold = maxThreshold;
	}

	
}
