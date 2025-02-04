package rimenergyapi.security.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PasswordConfirmation {

	@NotNull(message = "ID USER must not be null")
	private Integer id;

	@Size(min = 4, message = "Minimum password length: 4 characters")
	@NotNull(message = "Password must not be null")
	private String oldPassword;

	@Size(min = 4, message = "Minimum password length: 4 characters")
	@NotNull(message = "Password must not be null")
	private String newPassword;

	private Boolean receiveNotification;

	public PasswordConfirmation() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Boolean getReceiveNotification() {
		return receiveNotification;
	}

	public void setReceiveNotification(Boolean receiveNotification) {
		this.receiveNotification = receiveNotification;
	}

}
