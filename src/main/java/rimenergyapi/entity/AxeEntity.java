package rimenergyapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "axe", schema = "tricity", catalog = "")
public class AxeEntity {
    private short id;
    private String unity;

    @Id
    @Column(name = "id")
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "unity")
    public String getUnity() {
        return unity;
    }

    public void setUnity(String unity) {
        this.unity = unity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AxeEntity axeEntity = (AxeEntity) o;
        return id == axeEntity.id &&
                Objects.equals(unity, axeEntity.unity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, unity);
    }
}
