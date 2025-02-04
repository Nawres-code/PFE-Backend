package rimenergyapi.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gaz", schema = "tricity", catalog = "")
public class GazEntity {
    private int id;
    private DeviceEntity device;
    private String name;
    private InstallationEntity installation;
    private String outputNumber;
    
    @Id
    @Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="device_id", nullable = false, updatable = true, insertable = true)
    public DeviceEntity getDevice() {
		return device;
	}

	public void setDevice(DeviceEntity device) {
		this.device = device;
	}
    @Basic
    @Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="installation_id", nullable=false)
    public InstallationEntity getInstallation() {
        return this.installation;
    }
    
    public void setInstallation(InstallationEntity installation) {
        this.installation = installation;
    }
	
    @Basic
    @Column(name = "output_number")
	public String getOutputNumber() {
		return outputNumber;
	}
	public void setOutputNumber(String outputNumber) {
		this.outputNumber = outputNumber;
	}
    
    
}
