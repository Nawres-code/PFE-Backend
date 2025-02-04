package rimenergyapi.dto.accounts;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDto {
	private Long id;

	private String username;

	private String password;

	private String displayName;
	
	private List<Integer> installationsIds = new ArrayList<Integer>();
	
	private List<AuthorityInfoDto> authorities = new ArrayList<AuthorityInfoDto>();
	
	private Timestamp creationDate;
	
	private boolean isRoot;
	
	private boolean enabled;

	
	public UserInfoDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserInfoDto(Long id, String username, String password, String displayName) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.displayName = displayName;
		this.installationsIds=new ArrayList<>();
		this.isRoot=true;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}


	public List<Integer> getInstallationsIds() {
		return installationsIds;
	}

	public void setInstallationsIds(List<Integer> installationsIds) {
		this.installationsIds = installationsIds;
	}

	public boolean isRoot() {
		return isRoot;
	}

	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}

	public List<AuthorityInfoDto> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(List<AuthorityInfoDto> authorities) {
		this.authorities = authorities;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
	
}
