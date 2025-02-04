package rimenergyapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "category", schema = "tricity", catalog = "")
public class CategoryEntity {
    private int id;
    private String name;
    private String color;
    private String icon;
    private String type;
    private Integer tenantId;
   
    public CategoryEntity() {
	}

	public CategoryEntity(int id, String name, String color, String icon) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.icon = icon;
	}
	
	public CategoryEntity(int id, String name, String color, String icon, String type) {
		this.id = id;
		this.name = name;
		this.color = color;
		this.icon = icon;
		this.type = type;
	}

	@Id
    @Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "icon")
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
		return type;
	}
    
    public void setType(String type) {
		this.type = type;
	}
    
    @Basic
    @Column(name = "tenant_id")
    public Integer getTenantId() {
		return tenantId;
	}
    
    public void setTenantId(Integer tenantId) {
		this.tenantId = tenantId;
	}
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(color, that.color) &&
                Objects.equals(icon, that.icon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color, icon);
    }
}
