package rimenergyapi.dto.accounts;

import java.util.Date;

public class ActionInfoDto {
	private Long id;

	private String type;

	private String details;

	private Date createdAt;
	
	private String ipAddress;
	
	private String agent;

	public Long getId() {
		return id;
	}

	public ActionInfoDto() {
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	
}
