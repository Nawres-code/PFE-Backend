package rimenergyapi.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "input", schema = "tricity", catalog = "")
public class InputEntity {
	private int id;
	private String name;
	private int idInputDevice;
	private DeviceEntity device;
	private InstallationEntity installation;
	private String color;
	private InputCategoryEntity category;
	private boolean inversed;
	private String alarmStatus;
	private Double alarmValue;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "id_input_device")
	public int getIdInputDevice() {
		return idInputDevice;
	}
	public void setIdInputDevice(int idInputDevice) {
		this.idInputDevice = idInputDevice;
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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "installation_id", nullable = true)
	public InstallationEntity getInstallation() {
		return installation;
	}
	public void setInstallation(InstallationEntity installation) {
		this.installation = installation;
	}
	
	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id", nullable=true)
	public InputCategoryEntity getCategory() {
		return category;
	}
	public void setCategory(InputCategoryEntity category) {
		this.category = category;
	}
	
	public boolean isInversed() {
		return inversed;
	}
	public void setInversed(boolean inversed) {
		this.inversed = inversed;
	}

	public String getAlarmStatus() {
		return alarmStatus;
	}
	public void setAlarmStatus(String alarmStatus) {
		this.alarmStatus = alarmStatus;
	}
	
	public Double getAlarmValue() {
		return alarmValue;
	}
	public void setAlarmValue(Double alarmValue) {
		this.alarmValue = alarmValue;
	}
	
}
