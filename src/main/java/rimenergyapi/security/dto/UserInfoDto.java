package rimenergyapi.security.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import rimenergyapi.dto.AuthorityInfoDto;
import rimenergyapi.security.entity.Role;
import rimenergyapi.security.entity.User;

public class UserInfoDto {
	
	public UserInfoDto() {
		this.installationIds=new ArrayList<Integer>();
	}

	private Integer id;

	@Size(min = 4, max = 255, message = "Minimum login length: 4 characters")
	@NotNull(message = "username must not be null")
	private String username;

	private String email;

	private String avatar;

	@Size(min = 4, max=40, message = "Minimum full name length: 4 characters & Maximum 50 characters")
	@NotNull(message = "full name must not be null")
	private String fullName;

	private String phone;

	private String fixe;

	private String address;

	private boolean root;

	private List<Role> roles;

	private String theme;
	
	private String token;
	
	private boolean enabled;
	
	private List<Integer> installationIds;
	
	private List<AuthorityInfoDto> authorities;
	
	private List<UserInfoDto> subAccounts;
	
	private int adminId;
	
	private String refreshToken;
	
	public int getAdminId() {
		return adminId;
	}
	
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
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

	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<Integer> getInstallationIds() {
		return installationIds;
	}

	public void setInstallationIds(List<Integer> installationIds) {
		this.installationIds = installationIds;
	}

	public List<AuthorityInfoDto> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<AuthorityInfoDto> authorities) {
		this.authorities = authorities;
	}	
	
	public List<UserInfoDto> getSubAccounts() {
		return subAccounts;
	}
	
	public void setSubAccounts(List<UserInfoDto> subAccounts) {
		this.subAccounts = subAccounts;
	}

	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}
}
