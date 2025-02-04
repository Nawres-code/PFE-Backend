package rimenergyapi.dto.data;

public class InstallationInfoDto {
    private int id;
    private String name;
    private boolean enabled;
    private int zoneId;
    private String zoneName;
    private int tenantId;
    private String type;
    
	public InstallationInfoDto() {
	}
	
	public InstallationInfoDto(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public InstallationInfoDto(int id, String name, boolean enabled) {
		this.id = id;
		this.name = name;
		this.enabled = enabled;
	}
	
	public InstallationInfoDto(int id, String name, boolean enabled, int zoneId, String zoneName) {
		this.id = id;
		this.name = name;
		this.enabled = enabled;
		this.zoneId = zoneId;
		this.zoneName = zoneName;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
    
	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}
	
	public int getZoneId() {
		return zoneId;
	}
	
	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}
	
	public String getZoneName() {
		return zoneName;
	}
	
	public int getTenantId() {
		return tenantId;
	}
	
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
}
