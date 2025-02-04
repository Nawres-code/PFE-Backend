package rimenergyapi.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import rimenergyapi.entity.Type.InstallationType;

@Entity
@Table(name = "installation", schema = "tricity", catalog = "")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "nature", discriminatorType = DiscriminatorType.STRING)
public class InstallationView {
  
	protected int id;
    protected String name;
    protected String gpsLat;
    protected String gpsLon;
    protected String type;
    protected int tenantId;
    protected Integer surface;
    protected String color;
    protected InstallationType nature;
    protected ZonesEntity zones ;

	
	public InstallationView() {
		super();
	}
	
    public InstallationView(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

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
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "gps_lat")
    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    @Basic
    @Column(name = "gps_lon")
    public String getGpsLon() {
        return gpsLon;
    }

    public void setGpsLon(String gpsLon) {
        this.gpsLon = gpsLon;
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
    @Column(name = "tenant_id")
    public int getTenantId() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId = tenantId;
    }

    @Basic
    @Column(name = "surface")
    public Integer getSurface() {
        return surface;
    }

    public void setSurface(Integer surface) {
        this.surface = surface;
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
	@Enumerated(EnumType.STRING)
    @Column(name = "nature", insertable = false, updatable = false)
    public InstallationType getNature() {
		return nature;
	}
    
    public void setNature(InstallationType nature) {
		this.nature = nature;
	}
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="zone_id")
    @JsonIgnore
    public ZonesEntity getZones() {
        return this.zones;
    }
    
    public void setZones(ZonesEntity zones) {
        this.zones = zones;
    }
    
    public InstallationView(int id) {
    	this.id = id;
    }
}
