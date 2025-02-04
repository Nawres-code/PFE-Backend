package rimenergyapi.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ACTION")
public class ActionEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "TYPE", length = 50)
	private String type;

	@Column(name = "DETAILS", length = 50)
	private String details;

	@Column(name = "CREATEDAT")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
	@Column(name = "ip_address")
	private String ipAddress;
	
	@Column(name="agent")
	private String agent;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private UserEntity user;

	public ActionEntity() {
	}

	public ActionEntity(Long id, String type, String details, Date createdAt ,String ipAddress, String agent ) {
		super();
		this.id = id;
		this.type = type;
		this.details = details;
		this.createdAt = createdAt;
		this.ipAddress = ipAddress;
		this.agent = agent;
	}

	public Long getId() {
		return id;
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Action {id:" + id + ", type:" + type + ", details:" + details + ", createdAt:" + createdAt + ", user:"
				+ user + "}";
	}


}

