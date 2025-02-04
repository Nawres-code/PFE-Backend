package rimenergyapi.dto;

import java.util.Set;

public class ProviderInstallationAllDto {

    private int id;
    private String name;
    private String gpsLat;
    private String gpsLon;
    private String type;
    private int tenantId;
    private Integer surface;
    private String color;
    private String nature;
    private Set<DeviceInfoDto> devices;
    private Set<GroupsInfoDto> groupses;

    
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
	public String getGpsLat() {
		return gpsLat;
	}
	public void setGpsLat(String gpsLat) {
		this.gpsLat = gpsLat;
	}
	public String getGpsLon() {
		return gpsLon;
	}
	public void setGpsLon(String gpsLon) {
		this.gpsLon = gpsLon;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getTenantId() {
		return tenantId;
	}
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}
	public Integer getSurface() {
		return surface;
	}
	public void setSurface(Integer surface) {
		this.surface = surface;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Set<DeviceInfoDto> getDevices() {
		return devices;
	}
	public void setDevices(Set<DeviceInfoDto> devices) {
		this.devices = devices;
	}
	public Set<GroupsInfoDto> getGroupses() {
		return groupses;
	}
	public void setGroupses(Set<GroupsInfoDto> groupses) {
		this.groupses = groupses;
	}
	
	public String getNature() {
		return nature;
	}
	public void setNature(String nature) {
		this.nature = nature;
	}
}
