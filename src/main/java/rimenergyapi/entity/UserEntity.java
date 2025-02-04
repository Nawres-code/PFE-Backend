package rimenergyapi.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "user", schema = "tricity", catalog = "")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String username;

	private String password;

	private String displayName;
	
	private Timestamp creationDate;
	
	private boolean enabled;

	private List<InstallationEntity> installations;
	
	private List<AuthorityEntity> authorities;
	
	private List<ActionEntity> actions;

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "displayName")
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	@ManyToMany(cascade = {}, fetch = FetchType.LAZY)
	@JoinTable(name = "user_installation", joinColumns = {
			@JoinColumn(name = "id_user", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_installation", referencedColumnName = "id") })
	public List<InstallationEntity> getInstallations() {
		return installations;
	}

	public void setInstallations(List<InstallationEntity> installations) {
		this.installations = installations;
	}

	@ManyToMany(cascade = {}, fetch = FetchType.LAZY)
	@JoinTable(name = "user_authority", joinColumns = {
			@JoinColumn(name = "id_user", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "id_authority", referencedColumnName = "id") })
	public List<AuthorityEntity> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<AuthorityEntity> authorites) {
		this.authorities = authorites;
	}

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	public List<ActionEntity> getActions() {
		return actions;
	}

	public void setActions(List<ActionEntity> actions) {
		this.actions = actions;
	}

	@Column(name = "created_at")
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
