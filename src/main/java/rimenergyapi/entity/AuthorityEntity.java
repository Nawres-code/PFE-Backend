package rimenergyapi.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "authority", schema = "tricity", catalog = "")
public class AuthorityEntity {
	private long id;
	private String name;
	private String label;
	private List<UserEntity> users;
	
	public AuthorityEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AuthorityEntity(long id, String name, String label) {
		super();
		this.id = id;
		this.name = name;
		this.label = label;
	}
	
	public AuthorityEntity(long id) {
		this.id = id;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name= "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name= "label")
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}

	@ManyToMany(targetEntity=UserEntity.class, mappedBy = "authorities", fetch = FetchType.EAGER)
	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}
	
	
	
}
