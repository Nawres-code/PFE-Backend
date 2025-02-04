package rimenergyapi.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "point")
@IdClass(PointId.class)
public class PointEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String label;
	private String unit;// °C,
	private Integer setpointId;
	private DeviceFroidEntity device;
	private InstallationEntity installation;
	private String type;

	@Id
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public void setSetpointId(Integer setpointId) {
		this.setpointId = setpointId;
	}

	@Column(name = "setpoint_id", nullable=true)
	public Integer getSetpointId() {
		return setpointId;
	}

	@Id
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn(name = "device_id")
	public DeviceFroidEntity getDevice() {
		return device;
	}

	public void setDevice(DeviceFroidEntity device) {
		this.device = device;
	}
	
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "installation_id", nullable = false)
	public InstallationEntity getInstallation() {
		return this.installation;
	}

	public void setInstallation(InstallationEntity installation) {
		this.installation = installation;
	}	

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((device == null) ? 0 : device.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this.id == ((PointEntity)(obj)).getId()&&this.device.getId()==((PointEntity)(obj)).getDevice().getId())
			return true;
		
		return false;
	}

	@Override
	public String toString() {
		return "Point [id=" + id + ", label=" + label + ", unit=" + unit + "]";
	}
}
	

