package rimenergyapi.dto;

public class InputInfoDto {
	private int id;
	private String name;
	private int idInputDevice;
	private DeviceInfoDto device;
	private String color;
	private InputCategoryInfoDto category;
	private boolean inversed;
	private String alarmStatus;
	private Double alarmValue;
	
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
	public int getIdInputDevice() {
		return idInputDevice;
	}
	public void setIdInputDevice(int idInputDevice) {
		this.idInputDevice = idInputDevice;
	}
	public DeviceInfoDto getDevice() {
		return device;
	}
	public void setDevice(DeviceInfoDto device) {
		this.device = device;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public InputCategoryInfoDto getCategory() {
		return category;
	}
	public void setCategory(InputCategoryInfoDto category) {
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
