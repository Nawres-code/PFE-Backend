package rimenergyapi.security.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true, nullable = false)
	private String username;

	private String fullName;

	private String phone;

	private String fixe;

	@Column(name = "ADDRESS", length = 200)
	private String address;

	@Column(name = "HAVE_AVATAR", columnDefinition = "tinyint(1) default 0")
	private boolean haveAvatar;

	@Column(unique = true, nullable = true)
	private String email;

	private String password;

	private String rawPassword;

	private String avatar;
	
	private LocalDateTime createdAt;

	@ElementCollection(fetch = FetchType.EAGER)
	@Enumerated(EnumType.STRING)
	List<Role> roles;

	@Column(name = "ENABLE", columnDefinition = "tinyint(1) default 1")
	private boolean enable;

	private String theme;
	
	private boolean root;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.SUBSELECT)
	@JoinTable(
			  name = "sub_users",
			  joinColumns = @JoinColumn(name = "root_id"),
			  inverseJoinColumns = @JoinColumn(name = "sub_id"))
	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//@Fetch(value = FetchMode.SUBSELECT)
	//@JoinColumn(name = "root_Id")

	private List<User> subAccounts = new ArrayList<>();
	
	@Transient
	private int idAdmin;
	
	public int getIdAdmin() {
		return idAdmin;
	}
	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public User() {
		this.createdAt = LocalDateTime.now();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getRawPassword() {
		return rawPassword;
	}

	public void setRawPassword(String rawPassword) {
		this.rawPassword = rawPassword;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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

	public boolean isHaveAvatar() {
		return haveAvatar;
	}

	public void setHaveAvatar(boolean haveAvatar) {
		this.haveAvatar = haveAvatar;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}
	
	
	public List<User> getSubAccounts() {
		return subAccounts;
	}
	
	public void setSubAccounts(List<User> subAccounts) {
		this.subAccounts = subAccounts;
	}
	

}
