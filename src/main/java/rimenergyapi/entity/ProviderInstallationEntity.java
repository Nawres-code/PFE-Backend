package rimenergyapi.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("provider")
public class ProviderInstallationEntity extends InstallationView {
    private List<DeviceEntity> devices ;
    private List<GroupsEntity> groupses;
	
	public ProviderInstallationEntity() {
		super();
	}
	
    public ProviderInstallationEntity(int id, String name) {
		super();
		this.setId(id);
		this.setName(name);
	}

    @OneToMany(fetch=FetchType.LAZY, mappedBy="installation", orphanRemoval = true, cascade = CascadeType.ALL)
    public List<DeviceEntity> getDevices() {
        return this.devices;
    }
    
    public void setDevices(List<DeviceEntity> devices) {
        this.devices = devices;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="installation", orphanRemoval = true, cascade = CascadeType.ALL)
    public List<GroupsEntity> getGroupses() {
        return this.groupses;
    }
    
    public void setGroupses(List<GroupsEntity> groupses) {
        this.groupses = groupses;
    }
	
	public GroupsEntity getGroupById(int id) {
		for(GroupsEntity group : groupses) {
			if(group.getId()==id) {
				return group;
			}
		}
		return null;
	}
	
	
}
