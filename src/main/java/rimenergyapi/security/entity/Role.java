package rimenergyapi.security.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	ROLE_ADMIN("ROLE_ADMIN"), ROLE_CLIENT("ROLE_CLIENT"), ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN"), ROLE_COPY_RO("ROLE_COPY_RO");

	public String getAuthority() {
		return name();
	}

	private String value;

	private Role(String state) {
		this.value = state;
	}

	public String getValue() {
		return value;
	}

}
