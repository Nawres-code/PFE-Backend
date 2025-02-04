package rimenergyapi.entity;

import javax.persistence.*;

import org.hibernate.annotations.Where;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "zones", schema = "tricity", catalog = "")
public class ZonesEntity {
    private int idZone;
    private String name;
    private int tenantId;
    private String type;
    private Set<InstallationEntity> installations ;

    public ZonesEntity(int idZone) {
    	this.idZone = idZone;
    }
    public ZonesEntity() {
    }

	@Id
    @Column(name = "id_zone")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getIdZone() {
        return idZone;
    }

    public void setIdZone(int idZone) {
        this.idZone = idZone;
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    @OneToMany(fetch=FetchType.LAZY,
    		cascade = CascadeType.ALL,
    		mappedBy="zones")
    @Where(clause = "nature='consumption'")
    public Set<InstallationEntity> getInstallations() {
        return this.installations;
    }
    
    public void setInstallations(Set<InstallationEntity> installations) {
        this.installations = installations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZonesEntity that = (ZonesEntity) o;
        return idZone == that.idZone &&
                tenantId == that.tenantId &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idZone, name, tenantId);
    }
}
