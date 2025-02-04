package rimenergyapi.entity;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "phase", schema = "tricity", catalog = "")
public class PhaseEntity {
	
    private int id;
    private Integer confId;
    private DeviceEntity device;
    private String color;
    private int idPhaseDevice;
    private VoltageEntity voltage;
    private String name;
    private int fixedSign;
    private byte isFictif;
    private String formule;
    private Set<GroupsEntity> groupses = new HashSet<GroupsEntity>();

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
    @Column(name = "conf_id")
    public Integer getConfId() {
        return confId;
    }

    public void setConfId(Integer confId) {
        this.confId = confId;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="device_id", nullable=false)
    public DeviceEntity getDevice() {
        return this.device;
    }
    
    public void setDevice(DeviceEntity device) {
        this.device = device;
    }

    @Basic
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Basic
    @Column(name = "id_phase_device")
    public int getIdPhaseDevice() {
        return idPhaseDevice;
    }

    public void setIdPhaseDevice(int idPhaseDevice) {
        this.idPhaseDevice = idPhaseDevice;
    }

    @ManyToOne(fetch=FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH} )
    @JoinColumn(name="voltage_id")
    public VoltageEntity getVoltage() {
		return voltage;
	}

	public void setVoltage(VoltageEntity voltage) {
		this.voltage = voltage;
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
    @Column(name = "fixed_sign")
    public int getFixedSign() {
        return fixedSign;
    }

    public void setFixedSign(int fixedSign) {
        this.fixedSign = fixedSign;
    }

    @Basic
    @Column(name = "is_fictif")
    public byte getIsFictif() {
        return isFictif;
    }

    public void setIsFictif(byte isFictif) {
        this.isFictif = isFictif;
    }

    @Basic
    @Column(name = "formule")
    public String getFormule() {
        return formule;
    }

    public void setFormule(String formule) {
        this.formule = formule;
    }
   
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
	    name="groups_phase", catalog="tricity", 
	    joinColumns = { @JoinColumn(name="phase_id",  nullable = false, insertable = false, updatable = false) },
	    inverseJoinColumns = { @JoinColumn(name="groups_id", nullable = false, insertable = false,  updatable = false) }
    )
    public Set<GroupsEntity> getGroupses() {
        return this.groupses;
    }
    
    public void setGroupses(Set<GroupsEntity> groupses) {
        this.groupses = groupses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhaseEntity that = (PhaseEntity) o;
        return id == that.id &&
                idPhaseDevice == that.idPhaseDevice &&
                fixedSign == that.fixedSign &&
                isFictif == that.isFictif &&
                Objects.equals(confId, that.confId) &&
                Objects.equals(color, that.color) &&
                Objects.equals(name, that.name) &&
                Objects.equals(formule, that.formule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, confId, color, idPhaseDevice, name, fixedSign, isFictif, formule);
    }
}
