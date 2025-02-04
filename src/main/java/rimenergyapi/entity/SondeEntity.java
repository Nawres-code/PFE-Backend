package rimenergyapi.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

@Entity
@Table(name = "sonde", schema = "tricity", catalog = "")
public class SondeEntity {
	private Double id;
	private String name;
	private Integer indexHumDevice = 0; 
	private DeviceEntity device;
	private InstallationEntity installation;
	private String type;
	private Integer minThreshold;
	private Integer maxThreshold;
	private String threshold;
	private String configuration;
	private String role;
	private String fictifId;


	public SondeEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SondeEntity(Double id,String name, Integer indexHumDevice
			, String type, Integer minThreshold, Integer maxThreshold, String role) {
		super();
		this.id = id;
		this.name = name;
		this.indexHumDevice = indexHumDevice;
		this.type = type;
		this.minThreshold = minThreshold;
		this.maxThreshold = maxThreshold;
		this.role = role;
	}



	@Id
	@Column(name = "id")
	public Double getId() {
		return id;
	}

	public void setId(Double Double) {
		this.id = Double;
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
	@Column(name = "index_Hum_Device")
	public Integer getIndexHumDevice() {
		return indexHumDevice;
	}

	public void setIndexHumDevice(Integer indexHumDevice) {
		this.indexHumDevice = indexHumDevice;
	}

	@Column(name = "min_threshold")
	public Integer getMinThreshold() {
		return minThreshold;
	}

	public void setMinThreshold(Integer minThreshold) {
		this.minThreshold = minThreshold;
	}

	@Column(name = "max_threshold")
	public Integer getMaxThreshold() {
		return maxThreshold;
	}

	public void setMaxThreshold(Integer maxThreshold) {
		this.maxThreshold = maxThreshold;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "device_id", nullable = true)
	public DeviceEntity getDevice() {
		return device;
	}

	public void setDevice(DeviceEntity device) {
		this.device = device;
	}

	@JsonIgnore
	@ManyToOne()
	@JoinColumn(name = "installation_id", nullable = true)
	public InstallationEntity getInstallation() {
		return this.installation;
	}

	public void setInstallation(InstallationEntity installation) {
		this.installation = installation;
	}

	@Basic
	@Column(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Column(name = "role")
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Column(name = "configuration")
	public String getConfiguration() {
		return configuration;
	}
	
	public void setConfiguration(String configuration) {
		this.configuration = configuration;
	}
	
	@Column(name="fictif_id")
	public String getFictifId() {
		return fictifId;
	}
	public void setFictifId(String fictifId) {
		this.fictifId = fictifId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SondeEntity that = (SondeEntity) o;
		return id == that.id &&

				Objects.equals(name, that.name) && Objects.equals(indexHumDevice, that.indexHumDevice);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, indexHumDevice);
	}
}
