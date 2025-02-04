package rimenergyapi.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "device", schema = "tricity", catalog = "")
public class DeviceEntity {
    private int id;
    private int nbPhase;
    private boolean enabled;
    private int lastId;
    private int tenantId;
    private String protocol;
    private String type;
    private String pool;
    private Boolean isFictif;
    private String name;
    private int period;
    private InstallationView installation;
    private Set<SondeEntity> sondes;
    private Set<PhaseEntity> phases;
    private Set<GazEntity> gazs;
    
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
    @Column(name = "nb_phase")
    public int getNbPhase() {
        return nbPhase;
    }

    public void setNbPhase(int nbPhase) {
        this.nbPhase = nbPhase;
    }

    @Basic
    @Column(name = "enabled")
    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Basic
    @Column(name = "last_id")
    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
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
    @Column(name = "protocol")
    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "pool")
    public String getPool() {
        return pool;
    }

    public void setPool(String pool) {
        this.pool = pool;
    }

    @Basic
    @Column(name = "is_fictif", nullable = true)
    public Boolean getIsFictif() {
        return isFictif;
    }

    public void setIsFictif(Boolean isFictif) {
        this.isFictif = isFictif;
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
    @Column(name = "period")
    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="installation_id", nullable=false)
    public InstallationView getInstallation() {
        return this.installation;
    }
    
    public void setInstallation(InstallationView installation) {
        this.installation = installation;
    }
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy="device", cascade = CascadeType.ALL)
    public Set<SondeEntity> getSondes() {
        return this.sondes;
    }
    
    public void setSondes(Set<SondeEntity> sondes) {
        this.sondes = sondes;
    }
    
    @OneToMany(fetch=FetchType.EAGER, mappedBy="device", cascade = CascadeType.ALL)
    public Set<PhaseEntity> getPhases() {
        return this.phases;
    }
    
    public void setPhases(Set<PhaseEntity> phases) {
        this.phases = phases;
    }
    
    @OneToMany(fetch=FetchType.LAZY, mappedBy="device",
    		 cascade = CascadeType.ALL)
    public Set<GazEntity> getGazs() {
		return gazs;
	}

	public void setGazs(Set<GazEntity> gazs) {
		this.gazs = gazs;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceEntity that = (DeviceEntity) o;
        return id == that.id &&
                nbPhase == that.nbPhase &&
                enabled == that.enabled &&
                lastId == that.lastId &&
                tenantId == that.tenantId &&
                period == that.period &&
                Objects.equals(protocol, that.protocol) &&
                Objects.equals(type, that.type) &&
                Objects.equals(pool, that.pool) &&
                Objects.equals(isFictif, that.isFictif) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nbPhase, enabled, lastId, tenantId, protocol, type, pool, isFictif, name, period);
    }
}
