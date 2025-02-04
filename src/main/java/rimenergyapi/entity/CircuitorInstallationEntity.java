package rimenergyapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "circuitor_installation", schema = "tricity", catalog = "")
public class CircuitorInstallationEntity {
    private int idInstallation;
    private String circuitorId;

    @Id
    @Column(name = "id_installation")
    public int getIdInstallation() {
        return idInstallation;
    }

    public void setIdInstallation(int idInstallation) {
        this.idInstallation = idInstallation;
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
        CircuitorInstallationEntity that = (CircuitorInstallationEntity) o;
        return idInstallation == that.idInstallation &&
                Objects.equals(circuitorId, that.circuitorId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idInstallation, circuitorId);
    }
}
