package rimenergyapi.dto;

public class PhaseInfoDto {
	private int id;
	private String color;
    private int idPhaseDevice;
    private String name;
    private int fixedSign;
    private boolean isFictif;
    private String formule;
    private DeviceInfoDto device;
    private VoltageInfoDto voltage;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getIdPhaseDevice() {
		return idPhaseDevice;
	}
	public void setIdPhaseDevice(int idPhaseDevice) {
		this.idPhaseDevice = idPhaseDevice;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFixedSign() {
		return fixedSign;
	}
	public void setFixedSign(int fixedSign) {
		this.fixedSign = fixedSign;
	}
	public boolean isFictif() {
		return isFictif;
	}
	public void setFictif(boolean isFictif) {
		this.isFictif = isFictif;
	}
	public String getFormule() {
		return formule;
	}
	public void setFormule(String formule) {
		this.formule = formule;
	}
	public DeviceInfoDto getDevice() {
		return device;
	}
	public void setDevice(DeviceInfoDto device) {
		this.device = device;
	}
	public VoltageInfoDto getVoltage() {
		return voltage;
	}
	public void setVoltage(VoltageInfoDto voltage) {
		this.voltage = voltage;
	}

}
