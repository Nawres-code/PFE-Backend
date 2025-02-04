package rimenergyapi.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@DiscriminatorValue("consumption")
public class InstallationEntity extends InstallationView {
	
    private Set<DeviceEntity> devices;
    private Set<SondeEntity> sondes;
    private Set<GroupsEntity> groupses;
    private Set<InstallationGroupEntity> installationGroups;
    private Set<PointEntity> points ;
    private Set<DeviceFroidEntity> deviceFroids;
    private Set<GazEntity> gazs;
	private List<UserEntity> users;
	private Set<OutputEntity> outputs; 
	private boolean outputMode;
	private ProviderInstallationEntity provider;
	private Set<StationEntity> stations;
	private Set<InputEntity> inputs;
	private Set<IOEntity> IoList;
	private boolean enabled;
	
	public InstallationEntity() {
		super();
	}
	
    public InstallationEntity(int id, String name) {
		super();
		this.setId(id);
		this.setName(name);
	}
    public InstallationEntity(int id) {
    	this.id = id;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="installation",
    		 orphanRemoval = true, cascade = CascadeType.ALL)
    
    public Set<DeviceEntity> getDevices() {
        return this.devices;
    }
    
    public void setDevices(Set<DeviceEntity> devices) {
        this.devices = devices;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="installation",
    		 orphanRemoval = true, cascade = CascadeType.ALL)
    public Set<SondeEntity> getSondes() {
        return this.sondes;
    }
    
    public void setSondes(Set<SondeEntity> sondes) {
        this.sondes = sondes;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="installation",
    		 orphanRemoval = true, cascade = CascadeType.ALL)
    public Set<GroupsEntity> getGroupses() {
        return this.groupses;
    }
    
    public void setGroupses(Set<GroupsEntity> groupses) {
        this.groupses = groupses;
    }
    
    @ManyToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name="installationgroup_installation", catalog="tricity", joinColumns = { 
        @JoinColumn(name="installation_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="installationgroup_id", nullable=false, updatable=false) })
    public Set<InstallationGroupEntity> getInstallationGroups() {
		return installationGroups;
	}

	public void setInstallationGroups(Set<InstallationGroupEntity> installationGroups) {
		this.installationGroups = installationGroups;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="installation", 
			orphanRemoval = true, cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
    public Set<PointEntity> getPoints() {
		return points;
	}

	public void setPoints(Set<PointEntity> points) {
		this.points = points;
	}

	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="installation",  
			orphanRemoval = true, cascade = CascadeType.ALL)
	 
	public Set<DeviceFroidEntity> getDeviceFroids() {
		return deviceFroids;
	}

	public void setDeviceFroids(Set<DeviceFroidEntity> deviceFroids) {
		this.deviceFroids = deviceFroids;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="installation", 
			orphanRemoval = true, cascade = CascadeType.ALL)
	 
	public Set<GazEntity> getGazs() {
		return gazs;
	}

	public void setGazs(Set<GazEntity> gazs) {
		this.gazs = gazs;
	}
	
	@ManyToMany(targetEntity=UserEntity.class, mappedBy = "installations", fetch = FetchType.EAGER)
	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
	
	@OneToOne(optional = true)//, orphanRemoval = true, cascade = CascadeType.ALL)   
	@JoinColumn(name="provider_id", unique=true, nullable=true, updatable=false)
	public ProviderInstallationEntity getProvider() {
		return provider;
	}
	
	public void setProvider(ProviderInstallationEntity provider) {
		this.provider = provider;
	}
	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="installation",
    		orphanRemoval = true, cascade = CascadeType.ALL)
    
	public Set<InputEntity> getInputs() {
		return inputs;
	}
	
	public void setInputs(Set<InputEntity> inputs) {
		this.inputs = inputs;
	}

	
    @OneToMany(fetch=FetchType.LAZY, mappedBy="installation",
    		orphanRemoval = true, cascade = CascadeType.ALL)
	public Set<IOEntity> getIoList() {
		return IoList;
	}
    public void setIoList(Set<IOEntity> ioList) {
		IoList = ioList;
	}

	/*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstallationEntity that = (InstallationEntity) o;
        return id == that.id &&
                tenantId == that.tenantId &&
                Objects.equals(name, that.name) &&
                Objects.equals(gpsLat, that.gpsLat) &&
                Objects.equals(gpsLon, that.gpsLon) &&
                Objects.equals(type, that.type) &&
                Objects.equals(surface, that.surface) &&
                Objects.equals(color, that.color);
    }


	@Override
    public int hashCode() {
        return Objects.hash(id, name, gpsLat, gpsLon, type, tenantId, surface, color);
    }*/
	
	
	public GroupsEntity getGroupById(int id) {
		for(GroupsEntity group : groupses) {
			if(group.getId()==id) {
				return group;
			}
		}
		return null;
	}
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="installation",  
			orphanRemoval = true, cascade = CascadeType.ALL)
	 
	public Set<OutputEntity> getOutputs() {
		return outputs;
	}
	public void setOutputs(Set<OutputEntity> outputs) {
		this.outputs = outputs;
	}
	
	@Column(name="outputs_auto_mode")
	public boolean isOutputMode() {
		return outputMode;
	}

	public void setOutputMode(boolean outputMode) {
		this.outputMode = outputMode;
	}

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="installation_station", catalog="tricity", joinColumns = { 
        @JoinColumn(name="installation_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="station_id", nullable=false, updatable=false) })
    
	// @OneToMany(fetch=FetchType.LAZY, mappedBy="installation")
    public Set<StationEntity> getStations() {
		return stations;
	}

	public void setStations(Set<StationEntity> stations) {
		this.stations = stations;
	}
	
	@Column(name = "enabled")
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
