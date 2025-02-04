package rimenergyapi.entity;

import rimenergyapi.entity.Type.AlertType;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "alert", schema = "tricity")
public class AlertEntity {
    private int id;
    private String name;
    private int tenantId;
    private String level;
    private int timeToAlert;
    private int timeToRealert;
    private String status;
    private AlertType type;

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
    @Column(name = "tenant_id")
    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    @Basic
    @Column(name = "level")
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Basic
    @Column(name = "time_to_alert")
    public int getTimeToAlert() {
        return timeToAlert;
    }

    public void setTimeToAlert(int timeToAlert) {
        this.timeToAlert = timeToAlert;
    }

    @Basic
    @Column(name = "time_to_realert")
    public int getTimeToRealert() {
        return timeToRealert;
    }

    public void setTimeToRealert(int timeToRealert) {
        this.timeToRealert = timeToRealert;
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
    @Column(name = "type")
    public AlertType getType() {
        return type;
    }

    public void setType(AlertType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlertEntity that = (AlertEntity) o;
        return id == that.id &&
                tenantId == that.tenantId &&
                timeToAlert == that.timeToAlert &&
                timeToRealert == that.timeToRealert &&
                Objects.equals(name, that.name) &&
                Objects.equals(level, that.level) &&
                Objects.equals(status, that.status) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tenantId, level, timeToAlert, timeToRealert, status, type);
    }
}
