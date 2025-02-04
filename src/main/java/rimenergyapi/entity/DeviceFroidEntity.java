package rimenergyapi.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "device_froid")
public class DeviceFroidEntity {
	private int id;
	private String ipAdress;
	private String peripheral;
	private String adress;
	private String model;
	private String name;
	private boolean enabled;
	private String serial;
	private String label;
	private InstallationEntity installation;
    private List<PointEntity> points ;
    
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="ip_adress")
	public String getIpAdress() {
		return ipAdress;
	}
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}
	@Transient
	public String getPeripheral() {
		return peripheral;
	}
	public void setPeripheral(String peripheral) {
		this.peripheral = peripheral;
	}
	@Transient
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
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
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="installation_id", nullable=false)
	public InstallationEntity getInstallation() {
		return installation;
	}
	public void setInstallation(InstallationEntity installation) {
		this.installation = installation;
	}	
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="device")
	public List<PointEntity> getPoints() {
		return points;
	}
	public void setPoints(List<PointEntity> points) {
		this.points = points;
	}
	@Override
	public String toString() {
		return "Device [id=" + id + ", peripheral=" + peripheral + ", adress=" + adress + ", model=" + model + ", name="
				+ name + ", enabled=" + enabled + ", serial=" + serial + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adress == null) ? 0 : adress.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((peripheral == null) ? 0 : peripheral.hashCode());
		result = prime * result + ((serial == null) ? 0 : serial.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DeviceFroidEntity other = (DeviceFroidEntity) obj;
		if (adress == null) {
			if (other.adress != null)
				return false;
		} else if (!adress.equals(other.adress))
			return false;
		if (enabled != other.enabled)
			return false;
		if (id != other.id)
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (peripheral == null) {
			if (other.peripheral != null)
				return false;
		} else if (!peripheral.equals(other.peripheral))
			return false;
		if (serial == null) {
			if (other.serial != null)
				return false;
		} else if (!serial.equals(other.serial))
			return false;
		return true;
	}
	
	
	
	
}
