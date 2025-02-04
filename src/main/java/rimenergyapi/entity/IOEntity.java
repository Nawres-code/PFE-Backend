package rimenergyapi.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rimenergyapi.entity.Type.IOType;

@Entity
@Table(name = "io_table", schema = "tricity")
public class IOEntity {

	private int id;
	private String name;
	private String outputNumber;
	private String unit;
	private float impulseVal;
	private int tenantId;
	private String category;
	private IOType type;
	private DeviceEntity device;
	private InstallationEntity installation;
	

	@Id
	@Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "device_id", nullable = false)
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

	@Basic
	@Column(name = "output_number")
	public String getOutputNumber() {
		return outputNumber;
	}
	public void setOutputNumber(String outputNumber) {
		this.outputNumber = outputNumber;
	}

	// @Enumerated(EnumType.STRING)
	@Basic
	@Column(name = "unit")
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	@Basic
	@Column(name = "category")
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="io_type")
	public IOType getType() {
		return type;
	}
	public void setType(IOType type) {
		this.type = type;
	}

	@Basic
	@Column(name = "tenant_id")
	public int getTenantId() {
		return tenantId;
	}
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}

	@Basic
	@Column(name = "impulse_value")
	public float getImpulseVal() {
		return impulseVal;
	}
	public void setImpulseVal(float impulseVal) {
		this.impulseVal = impulseVal;
	}
	
	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "installation_id", nullable = false)
	public InstallationEntity getInstallation() {
		return this.installation;
	}
	public void setInstallation(InstallationEntity installation) {
		this.installation = installation;
	}

}
