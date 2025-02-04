package rimenergyapi.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "installation_ip", schema = "tricity", catalog = "")
public class InstallationIpEntity {
    private int installationId;
    private String ip;

    @Id
    @Column(name = "installation_id")
    public int getInstallationId() {
        return installationId;
    }

    public void setInstallationId(int installationId) {
        this.installationId = installationId;
    }

    @Basic
    @Column(name = "ip")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstallationIpEntity that = (InstallationIpEntity) o;
        return installationId == that.installationId &&
                Objects.equals(ip, that.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(installationId, ip);
    }
}
