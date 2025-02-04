package rimenergyapi.entity;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "voltage", schema = "tricity", catalog = "")
public class VoltageEntity {
    private int id;
    private String name;
    private String color;
    private Set<PhaseEntity> phases = new HashSet<PhaseEntity>(0);

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

    @OneToMany(fetch=FetchType.LAZY, mappedBy="voltage" ,
    		cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    public Set<PhaseEntity> getPhases() {
		return phases;
	}

	public void setPhases(Set<PhaseEntity> phases) {
		this.phases = phases;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VoltageEntity that = (VoltageEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }
}
