package rimenergyapi.security.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NotNull(message = "Principal must not be null")
public class UserAddDto {

	@Size(min = 4, max = 255, message = "Minimum login length: 4 characters")
	@NotNull(message = "username must not be null")
	private String username;

	private String email;

	@Size(min = 4, message = "Minimum password length: 4 characters")
	@NotNull(message = "Password must not be null")
	private String password;

	@Size(min = 4, max=40, message = "Minimum full name length: 4 characters & Maximum 50 characters")
	@NotNull(message = "full name must not be null")
	private String fullName;

	private String phone;

	private String fixe;

	@Size(max=200,message="Maximum address length: 200 characters")
	private String address;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFixe() {
		return fixe;
	}

	public void setFixe(String fixe) {
		this.fixe = fixe;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
