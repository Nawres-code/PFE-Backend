package rimenergyapi.dto;

import java.util.HashSet;
import java.util.Set;

public class UserAlertDTO {

	private int id;
	private String measureId;
	private String fatherId;
	private String measureName;
	private String measureType;
	private String type;
	private Boolean isActive;
	private String email;
	private Set<AlertConfigurationInfoDTO> configs = new HashSet<AlertConfigurationInfoDTO>();
	private String zoneId;
	private String installationId;
	private String sms;
	private String message;
    private Integer pendingPeriod;

	//private int timeToRealert;
	
	public UserAlertDTO() {}
	
	public UserAlertDTO(int id, String measureId, String measureName, String measureType, String type, Boolean isActive,
			String email) {
		super();
		this.id = id;
		this.measureId = measureId;
		this.measureName = measureName;
		this.measureType = measureType;
		this.type = type;
		this.isActive = isActive;
		this.email = email;
	}

	public UserAlertDTO(int id, String measureId, String fatherId, String measureName, String measureType, String type, Boolean isActive,
			String email) {
		super();
		this.id = id;
		this.measureId = measureId;
		this.fatherId = fatherId;
		this.measureName = measureName;
		this.measureType = measureType;
		this.type = type;
		this.isActive = isActive;
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getMeasureId() {
		return measureId;
	}

	public void setMeasureId(String mesureId) {
		this.measureId = mesureId;
	}
	
	public String getFatherId() {
		return fatherId;
	}
	
	public void setFatherId(String fatherId) {
		this.fatherId = fatherId;
	}

	public String getMeasureName() {
		return measureName;
	}

	public void setMeasureName(String mesureName) {
		this.measureName = mesureName;
	}

	public String getMeasureType() {
		return measureType;
	}

	public void setMeasureType(String mesureType) {
		this.measureType = mesureType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<AlertConfigurationInfoDTO> getConfigs() {
		return configs;
	}

	public void setConfigs(Set<AlertConfigurationInfoDTO> configs) {
		this.configs = configs;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getInstallationId() {
		return installationId;
	}
	
	public void setInstallationId(String installationId) {
		this.installationId = installationId;
	}
	
	public String getZoneId() {
		return zoneId;
	}
	
	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getSms() {
		return sms;
	}
	public void setSms(String sms) {
		this.sms = sms;
	}
	
	public Integer getPendingPeriod() {
		return pendingPeriod;
	}
	public void setPendingPeriod(Integer pendingPeriod) {
		this.pendingPeriod = pendingPeriod;
	}
	
//	public AlertType getName() {
//		return name;
//	}
//
//	public void setName(AlertType name) {
//		this.name = name;
//	}
//
//	public int getTimeToRealert() {
//		return timeToRealert;
//	}
//
//	public void setTimeToRealert(int timeToRealert) {
//		this.timeToRealert = timeToRealert;
//	}

//	public Boolean getEmailMode() {
//		return emailMode;
//	}
//
//	public void setEmailMode(Boolean emailMode) {
//		this.emailMode = emailMode;
//	}
	
//	public Boolean getIsByGroup() {
//		return isByGroup;
//	}
//
//	public void setIsByGroup(Boolean isByGroup) {
//		this.isByGroup = isByGroup;
//	}
}

