package rimenergyapi.entity;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "input_category", schema = "tricity", catalog = "")
public class InputCategoryEntity {
	 private int id;
	    private String name;
	    private String color;
	    private String icon;
	    private Set<InputEntity> inputs ;
	    private String type;

	   
	    public InputCategoryEntity() {
			super();
			// TODO Auto-generated constructor stub
		}

		public InputCategoryEntity(int id, String name, String color, String icon) {
			super();
			this.id = id;
			this.name = name;
			this.color = color;
			this.icon = icon;
		}
		
		public InputCategoryEntity(int id, String name, String color, String icon, String type) {
			super();
			this.id = id;
			this.name = name;
			this.color = color;
			this.icon = icon;
			this.type = type;
		}

		@Id
	    @Column(name = "id")
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

	    @OneToMany(fetch=FetchType.LAZY, mappedBy="category")
	    public Set<InputEntity> getInputs() {
			return inputs;
		}
	    
	    public void setInputs(Set<InputEntity> inputs) {
			this.inputs = inputs;
		}
	    @Basic
	    @Column(name = "type")
	    public String getType() {
			return type;
		}
	    public void setType(String type) {
			this.type = type;
		}
	    
	    @Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        CategoryEntity that = (CategoryEntity) o;
	        return id == that.getId() &&
	                Objects.equals(name, that.getName()) &&
	                Objects.equals(color, that.getColor()) &&
	                Objects.equals(icon, that.getIcon());
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(id, name, color, icon);
	    }
	    
	    
}
