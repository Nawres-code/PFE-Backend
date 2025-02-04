package rimenergyapi.dto.data;

public class SondeInfoDto {
    private Double id;
    private String name;
    private Integer indexHumDevice;
    private String type;
    private Integer minThreshold;
    private Integer maxThreshold;
    private String role;
	private String configuration;
	private String fictifId;
	private int installationId;
	private String installationName;

    
    public SondeInfoDto() {
    	super();
    }
    
	public SondeInfoDto(Double id, String name, Integer indexHumDevice, String type, Integer minThreshold,
			Integer maxThreshold, String role) {
		super();
		this.id = id;
		this.name = name;
		this.indexHumDevice = indexHumDevice;
		this.type = type;
		this.minThreshold = minThreshold;
		this.maxThreshold = maxThreshold;
		this.role = role;
	}
	
	public SondeInfoDto(Double id, String name, Integer indexHumDevice, String type, Integer minThreshold,
			Integer maxThreshold, String role, String configuration) {
		super();
		this.id = id;
		this.name = name;
		this.indexHumDevice = indexHumDevice;
		this.type = type;
		this.minThreshold = minThreshold;
		this.maxThreshold = maxThreshold;
		this.role = role;
		this.configuration = configuration;
	}
	
	public Double getId() {
		return id;
	}
	public void setId(Double id) {
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

    public String getConfiguration() {
		return configuration;
	}
    
    public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
    public int getInstallationId() {
		return installationId;
	}
    public void setInstallationId(int installationId) {
		this.installationId = installationId;
	}
    
    public String getFictifId() {
		return fictifId;
	}
    public void setFictifId(String fictifId) {
		this.fictifId = fictifId;
	}
    
    public String getInstallationName() {
		return installationName;
	}
    public void setInstallationName(String installationName) {
		this.installationName = installationName;
	}
}
