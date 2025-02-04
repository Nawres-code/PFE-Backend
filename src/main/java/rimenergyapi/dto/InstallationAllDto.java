package rimenergyapi.dto;

import java.util.Set;

import rimenergyapi.dto.data.SondeInfoDto;

public class InstallationAllDto {

    private int id;
    private String name;
    private String gpsLat;
    private String gpsLon;
    private String type;
    private int tenantId;
    private Integer surface;
    private String color;
    private Integer zoneId;
    private Set<DeviceInfoDto> devices;
    private Set<SondeInfoDto> sondes;
    private Set<GroupsInfoDto> groupses;
    private Set<InstallationsGroupInfoDto> installationGroups;
    private Set<PointInfoDto> points;
    private Set<DeviceFroidDto> deviceFroids;
    private Set<GazInfoDto> gazs;
    private Set<OutputDto> outputs;
    private ProviderInstallationAllDto provider;
    private String nature;
    private Set<StationInfoDto> stations;
    private Set<InputInfoDto> inputs;
    private Set<IOInfoDto> ioList;
    private boolean enabled;
    
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
	public Integer getZoneId() {
		return zoneId;
	}
	public void setZoneId(Integer zoneId) {
		this.zoneId = zoneId;
	}
	public Set<DeviceInfoDto> getDevices() {
		return devices;
	}
	public void setDevices(Set<DeviceInfoDto> devices) {
		this.devices = devices;
	}
	public Set<SondeInfoDto> getSondes() {
		return sondes;
	}
	public void setSondes(Set<SondeInfoDto> sondes) {
		this.sondes = sondes;
	}
	
	public Set<GroupsInfoDto> getGroupses() {
		return groupses;
	}
	public void setGroupses(Set<GroupsInfoDto> groupses) {
		this.groupses = groupses;
	}
	public Set<InstallationsGroupInfoDto> getInstallationGroups() {
		return installationGroups;
	}
	public void setInstallationGroups(Set<InstallationsGroupInfoDto> installationGroups) {
		this.installationGroups = installationGroups;
	}
	public Set<PointInfoDto> getPoints() {
		return points;
	}
	public void setPoints(Set<PointInfoDto> points) {
		this.points = points;
	}
	public Set<DeviceFroidDto> getDeviceFroids() {
		return deviceFroids;
	}
	public void setDeviceFroids(Set<DeviceFroidDto> deviceFroids) {
		this.deviceFroids = deviceFroids;
	}
	public Set<GazInfoDto> getGazs() {
		return gazs;
	}
	public void setGazs(Set<GazInfoDto> gazs) {
		this.gazs = gazs;
	}
	public void setOutputs(Set<OutputDto> outputs) {
		this.outputs = outputs;
	}
	public Set<OutputDto> getOutputs() {
		return outputs;
	}
	
	public ProviderInstallationAllDto getProvider() {
		return provider;
	}
	public void setProvider(ProviderInstallationAllDto provider) {
		this.provider = provider;
	}
	
	public String getNature() {
		return nature;
	}
	
	public void setNature(String nature) {
		this.nature = nature;
	}
	public Set<StationInfoDto> getStations() {
		return stations;
	}
	public void setStations(Set<StationInfoDto> stations) {
		this.stations = stations;
	}
	public Set<InputInfoDto> getInputs() {
		return inputs;
	}
	public void setInputs(Set<InputInfoDto> inputs) {
		this.inputs = inputs;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Set<IOInfoDto> getIoList() {
		return ioList;
	}
	public void setIoList(Set<IOInfoDto> ioList) {
		this.ioList = ioList;
	}
	
}
