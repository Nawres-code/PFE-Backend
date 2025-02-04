package rimenergyapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "circuitor_group", schema = "tricity", catalog = "")
public class CircuitorGroupEntity {
    private int groupId;
    private String circuitorId;

    @Id
    @Column(name = "group_id")
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "circuitor_id")
    public String getCircuitorId() {
        return circuitorId;
    }

    public void setCircuitorId(String circuitorId) {
        this.circuitorId = circuitorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CircuitorGroupEntity that = (CircuitorGroupEntity) o;
        return groupId == that.groupId &&
                Objects.equals(circuitorId, that.circuitorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, circuitorId);
    }
}
