package rimenergyapi.dto;

import rimenergyapi.entity.Type.AlertConfigurationType;

public class AlertConfigurationInfoDTO {
    private int id;
  //  private Byte isActive;
    private String operator;
    private String value1;
    private String value2;
    private AlertConfigurationType type;
    
	//private Object payload;
    
	public AlertConfigurationInfoDTO() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
//	public Byte getIsActive() {
//		return isActive;
//	}
//	public void setIsActive(Byte isActive) {
//		this.isActive = isActive;
//	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String  operator) {
		this.operator = operator;
	}
	public String getValue1() {
		return value1;
	}
	public void setValue1(String value1) {
		this.value1 = value1;
	}
	public String getValue2() {
		return value2;
	}
	public void setValue2(String value2) {
		this.value2 = value2;
	}
	public AlertConfigurationType getType() {
		return type;
	}
	public void setType(AlertConfigurationType alertConfigurationType) {
		this.type = alertConfigurationType;
	}
//	public Object getPayload() {
//		return payload;
//	}
//	public void setPayload(Object payload) {
//		this.payload = payload;
//	}
    
    
}
