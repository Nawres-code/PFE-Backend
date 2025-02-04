package rimenergyapi.dto;

import java.util.List;

public class DeviceFroidDto {
	private int id;
	private String ipAdress;
	private String peripheral;
	private String adress;
	private String model;
	private String name;
	private boolean enabled;
	private String serial;
	private String label;
	private List<PointInfoDto> points; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIpAdress() {
		return ipAdress;
	}
	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}
	public String getPeripheral() {
		return peripheral;
	}
	public void setPeripheral(String peripheral) {
		this.peripheral = peripheral;
	}
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
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public List<PointInfoDto> getPoints() {
		return points;
	}
	public void setPoints(List<PointInfoDto> points) {
		this.points = points;
	}
	
	
}
