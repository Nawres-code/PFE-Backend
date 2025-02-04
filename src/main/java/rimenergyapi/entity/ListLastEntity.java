package rimenergyapi.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "list_last", schema = "tricity", catalog = "")
public class ListLastEntity {
    private int idBoitier;
    private String lastTrame;
    private String oldTrame;
    private Timestamp lastTime;
    private String version;
    private Integer port;

    @Id
    @Column(name = "id_boitier")
    public int getIdBoitier() {
        return idBoitier;
    }

    public void setIdBoitier(int idBoitier) {
        this.idBoitier = idBoitier;
    }

    @Basic
    @Column(name = "last_trame")
    public String getLastTrame() {
        return lastTrame;
    }

    public void setLastTrame(String lastTrame) {
        this.lastTrame = lastTrame;
    }

    @Basic
    @Column(name = "old_trame")
    public String getOldTrame() {
        return oldTrame;
    }

    public void setOldTrame(String oldTrame) {
        this.oldTrame = oldTrame;
    }

    @Basic
    @Column(name = "last_time")
    public Timestamp getLastTime() {
        return lastTime;
    }

    public void setLastTime(Timestamp lastTime) {
        this.lastTime = lastTime;
    }

    @Basic
    @Column(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Basic
    @Column(name = "port")
    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListLastEntity that = (ListLastEntity) o;
        return idBoitier == that.idBoitier &&
                Objects.equals(lastTrame, that.lastTrame) &&
                Objects.equals(oldTrame, that.oldTrame) &&
                Objects.equals(lastTime, that.lastTime) &&
                Objects.equals(version, that.version) &&
                Objects.equals(port, that.port);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBoitier, lastTrame, oldTrame, lastTime, version, port);
    }
}
