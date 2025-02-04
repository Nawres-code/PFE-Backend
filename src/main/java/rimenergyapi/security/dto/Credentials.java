package rimenergyapi.security.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NotNull(message = "Credentials must not be null")
public class Credentials {

	@Size(min = 4, max = 255, message = "Minimum login length: 4 characters")
	@NotNull(message = "Login must not be null")
	private String login;

	@Size(min = 4, message = "Minimum password length: 4 characters")
	@NotNull(message = "Password must not be null")
	private String password;

	public Credentials() {
	}

	public Credentials(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
