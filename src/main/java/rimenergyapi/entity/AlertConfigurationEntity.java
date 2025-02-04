package rimenergyapi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rimenergyapi.entity.Type.AlertConfigurationType;

@Entity
@Table(name = "alertconfiguration_1", schema = "tricity", catalog = "")
public class AlertConfigurationEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String operator;
	private String value1;
	private String value2;
	private AlertConfigurationType type;
	private boolean isActive;
	private UserAlertEntity userAlert = new UserAlertEntity();

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "operator")
 // @Enumerated(EnumType.STRING)
	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	@Column(name = "value1")
	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}

	@Column(name = "value2")
	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	@Column(name = "type")
	@Enumerated(EnumType.STRING)
	public AlertConfigurationType getType() {
		return type;
	}

	public void setType(AlertConfigurationType type) {
		this.type = type;
	}

	@Column(name = "is_active")
	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "alert_id", nullable = false, updatable = true, insertable = true)
	public UserAlertEntity getUserAlert() {
		return userAlert;
	}

	public void setUserAlert(UserAlertEntity userAlert) {
		this.userAlert = userAlert;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((userAlert == null) ? 0 : userAlert.hashCode());
		result = prime * result + ((value1 == null) ? 0 : value1.hashCode());
		result = prime * result + ((value2 == null) ? 0 : value2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AlertConfigurationEntity other = (AlertConfigurationEntity) obj;
		if (id != other.id)
			return false;
		if (isActive != other.isActive)
			return false;
		if (operator != other.operator)
			return false;
		if (type != other.type)
			return false;
		if (userAlert == null) {
			if (other.userAlert != null)
				return false;
		} else if (!userAlert.equals(other.userAlert))
			return false;
		if (value1 == null) {
			if (other.value1 != null)
				return false;
		} else if (!value1.equals(other.value1))
			return false;
		if (value2 == null) {
			if (other.value2 != null)
				return false;
		} else if (!value2.equals(other.value2))
			return false;
		return true;
	}

}