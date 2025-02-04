package rimenergyapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mesure", schema = "tricity", catalog = "")
public class MesureEntity {
    private short id;
    private Short axeId;
    private String name;

    @Id
    @Column(name = "id")
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "axe_id")
    public Short getAxeId() {
        return axeId;
    }

    public void setAxeId(Short axeId) {
        this.axeId = axeId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MesureEntity that = (MesureEntity) o;
        return id == that.id &&
                Objects.equals(axeId, that.axeId) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, axeId, name);
    }
}
