package rimenergyapi.dto;

import rimenergyapi.entity.Type.IOType;

public class IOInfoDto {
	
	private int id; 
	private String name;
	private String outputNumber;
	private String unit;
	private float impulseVal;
	private IOType type;
	private String category;
	
	
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
	
	public String getOutputNumber() {
		return outputNumber;
	}
	public void setOutputNumber(String outputNumber) {
		this.outputNumber = outputNumber;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public float getImpulseVal() {
		return impulseVal;
	}
	public void setImpulseVal(float impulseVal) {
		this.impulseVal = impulseVal;
	}
	
	public IOType getType() {
		return type;
	}
	public void setType(IOType type) {
		this.type = type;
	}
	    
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
