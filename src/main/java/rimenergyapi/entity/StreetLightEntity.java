package rimenergyapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "street_light", schema = "tricity", catalog = "")
public class StreetLightEntity {
    private int id;
    private int outputId;
    private int groupsId;
    private double localizationNorth;
    private double localizationEast;
    private String status;
    private int idUser;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "output_id")
    public int getOutputId() {
        return outputId;
    }

    public void setOutputId(int outputId) {
        this.outputId = outputId;
    }

    @Basic
    @Column(name = "groups_id")
    public int getGroupsId() {
        return groupsId;
    }

    public void setGroupsId(int groupsId) {
        this.groupsId = groupsId;
    }

    @Basic
    @Column(name = "localization_north")
    public double getLocalizationNorth() {
        return localizationNorth;
    }

    public void setLocalizationNorth(double localizationNorth) {
        this.localizationNorth = localizationNorth;
    }

    @Basic
    @Column(name = "localization_east")
    public double getLocalizationEast() {
        return localizationEast;
    }

    public void setLocalizationEast(double localizationEast) {
        this.localizationEast = localizationEast;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "id_user")
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StreetLightEntity that = (StreetLightEntity) o;
        return id == that.id &&
                outputId == that.outputId &&
                groupsId == that.groupsId &&
                Double.compare(that.localizationNorth, localizationNorth) == 0 &&
                Double.compare(that.localizationEast, localizationEast) == 0 &&
                idUser == that.idUser &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, outputId, groupsId, localizationNorth, localizationEast, status, idUser);
    }
}
