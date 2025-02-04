package rimenergyapi.dto;

import java.util.List;

import rimenergyapi.entity.Type.AlertType;

public class AlertInfoDto {
	private int id;

	private AlertType name;

	private int timeToRealert;

	private String type;

	private Boolean isActive;

	private Boolean emailMode;

	private String emails;

	private List<AlertConfigurationInfoDTO> alertConfigurations;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public AlertType getName() {
		return name;
	}

	public void setName(AlertType name) {
		this.name = name;
	}

	public int getTimeToRealert() {
		return timeToRealert;
	}

	public void setTimeToRealert(int timeToRealert) {
		this.timeToRealert = timeToRealert;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Boolean getEmailMode() {
		return emailMode;
	}

	public void setEmailMode(Boolean emailMode) {
		this.emailMode = emailMode;
	}

	public String getEmails() {
		return emails;
	}

	public void setEmails(String emails) {
		this.emails = emails;
	}

	public List<AlertConfigurationInfoDTO> getAlertConfigurations() {
		return alertConfigurations;
	}

	public void setAlertConfigurations(List<AlertConfigurationInfoDTO> alertConfigurations) {
		this.alertConfigurations = alertConfigurations;
	}
	
	
}
