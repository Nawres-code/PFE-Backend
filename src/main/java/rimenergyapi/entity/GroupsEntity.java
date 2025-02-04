package rimenergyapi.entity;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "`groups`", schema = "tricity")
public class GroupsEntity {
    private int id;
    private int nbPhase;
    private byte enabled;
    private String name;
    private String color;
    private int tenantId;
    private String type;
    private int categoryId;
    private Integer threshold;
    private Integer thresholdDay;
    private Integer thresholdNight;
    private Integer thresholdWeek;
    private Integer thresholdWeekDays;
    private Integer thresholdWeekend;
    private Integer thresholdInstCuurent;
    private Integer x;
    private Integer y;
    private Integer z;
    private Integer hauteur;
    private Integer largeur;
    private InstallationView installation;
    private Set<PhaseEntity> phases;
    private GroupsEntity parent ;
    private List<GroupsEntity> children;


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
    public byte getEnabled() {
        return enabled;
    }

    public void setEnabled(byte enabled) {
        this.enabled = enabled;
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
    @Column(name = "color")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    @Basic
    @Column(name = "category_id")
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Basic
    @Column(name = "threshold")
    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }
    
    @Basic
    @Column(name = "threshold_day")
    public Integer getThresholdDay() {
        return thresholdDay;
    }

    public void setThresholdDay(Integer thresholdDay) {
        this.thresholdDay = thresholdDay;
    }

    @Basic
    @Column(name = "threshold_night")
    public Integer getThresholdNight() {
        return thresholdNight;
    }

    public void setThresholdNight(Integer thresholdNight) {
        this.thresholdNight = thresholdNight;
    }

    @Basic
    @Column(name = "threshold_week")
    public Integer getThresholdWeek() {
        return thresholdWeek;
    }

    public void setThresholdWeek(Integer thresholdWeek) {
        this.thresholdWeek = thresholdWeek;
    }

    @Basic
    @Column(name = "threshold_week_days")
    public Integer getThresholdWeekDays() {
        return thresholdWeekDays;
    }

    public void setThresholdWeekDays(Integer thresholdWeekDays) {
        this.thresholdWeekDays = thresholdWeekDays;
    }

    @Basic
    @Column(name = "threshold_weekend")
    public Integer getThresholdWeekend() {
        return thresholdWeekend;
    }

    public void setThresholdWeekend(Integer thresholdWeekend) {
        this.thresholdWeekend = thresholdWeekend;
    }

    @Basic
    @Column(name = "threshold_inst_cuurent")
    public Integer getThresholdInstCuurent() {
        return thresholdInstCuurent;
    }

    public void setThresholdInstCuurent(Integer thresholdInstCuurent) {
        this.thresholdInstCuurent = thresholdInstCuurent;
    }
	
    @Basic
    @Column(name = "x")
    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    @Basic
    @Column(name = "y")
    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Basic
    @Column(name = "z")
    public Integer getZ() {
        return z;
    }

    public void setZ(Integer z) {
        this.z = z;
    }

    @Basic
    @Column(name = "hauteur")
    public Integer getHauteur() {
        return hauteur;
    }

    public void setHauteur(Integer hauteur) {
        this.hauteur = hauteur;
    }

    @Basic
    @Column(name = "largeur")
    public Integer getLargeur() {
        return largeur;
    }

    public void setLargeur(Integer largeur) {
        this.largeur = largeur;
    }

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "father_id", referencedColumnName = "id", nullable = true, updatable = true, insertable = true)
    public GroupsEntity getParent() {
		return parent;
	}

	public void setParent(GroupsEntity parent) {
		this.parent = parent;
	}

	@OneToMany(mappedBy = "parent", fetch = FetchType.EAGER,
			orphanRemoval=true, cascade = CascadeType.ALL)
	public List<GroupsEntity> getChildren() {
		return children;
	}

	public void setChildren(List<GroupsEntity> children) {
		this.children = children;
	}
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="installation_id", nullable=false)
    public InstallationView getInstallation() {
        return this.installation;
    }
    
    public void setInstallation(InstallationView installation) {
        this.installation = installation;
    }

   
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name="groups_phase", catalog="tricity", 
    joinColumns = {@JoinColumn(name="groups_id")}, 
    inverseJoinColumns = {@JoinColumn(name="phase_id")})
    public Set<PhaseEntity> getPhases() {
        return this.phases;
    }
    
    public void setPhases(Set<PhaseEntity> phases) {
        this.phases = phases;
    }
  
    public void removePhase(PhaseEntity phase) {
        this.phases.remove(phase);
        phase.getGroupses().remove(this);
    }
   
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupsEntity that = (GroupsEntity) o;
        return id == that.id &&
                nbPhase == that.nbPhase &&
                enabled == that.enabled &&
                tenantId == that.tenantId &&
                categoryId == that.categoryId &&
                Objects.equals(name, that.name) &&
                Objects.equals(color, that.color) &&
                //Objects.equals(type, that.type) &&
                Objects.equals(threshold, that.threshold) &&
                /*Objects.equals(thresholdDay, that.thresholdDay) &&
                Objects.equals(thresholdNight, that.thresholdNight) &&
                Objects.equals(thresholdWeek, that.thresholdWeek) &&
                Objects.equals(thresholdWeekDays, that.thresholdWeekDays) &&
                Objects.equals(thresholdWeekend, that.thresholdWeekend) &&
                Objects.equals(thresholdInstCuurent, that.thresholdInstCuurent) &&*/
                Objects.equals(x, that.x) &&
                Objects.equals(y, that.y) &&
                Objects.equals(z, that.z) &&
                Objects.equals(hauteur, that.hauteur) &&
                Objects.equals(largeur, that.largeur);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nbPhase, enabled, name, color, tenantId, /*type,*/ categoryId, threshold,/* thresholdDay, thresholdNight, thresholdWeek, thresholdWeekDays, thresholdWeekend, thresholdInstCuurent,*/ x, y, z, hauteur, largeur);
    }

	@Override
	public String toString() {
		return "GroupsEntity [id=" + id + ", nbPhase=" + nbPhase + ", enabled=" + enabled + ", name=" + name
				+ ", color=" + color + ", tenantId=" + tenantId + ", type=" + type + ", categoryId=" + categoryId
				+ ", threshold=" + threshold + ", thresholdDay=" + thresholdDay + ", thresholdNight=" + thresholdNight
				+ ", thresholdWeek=" + thresholdWeek + ", thresholdWeekDays=" + thresholdWeekDays
				+ ", thresholdWeekend=" + thresholdWeekend + ", thresholdInstCuurent=" + thresholdInstCuurent + ", x="
				+ x + ", y=" + y + ", z=" + z + ", hauteur=" + hauteur + ", largeur=" + largeur + ", installation="
				+ installation + ", phases=" + phases + ", parent=" + parent + ", children=" + children + "]";
	}
	
	public GroupsEntity() {}

    
}
