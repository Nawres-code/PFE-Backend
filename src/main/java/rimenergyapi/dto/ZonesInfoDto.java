package rimenergyapi.dto;

import java.util.List;

public class ZonesInfoDto {
	
    private int idZone;
    private String name;
    private int tenantId;
    private List<InstallationAllDto> installations;
    
	public int getIdZone() {
		return idZone;
	}
	public void setIdZone(int idZone) {
		this.idZone = idZone;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getTenantId() {
		return tenantId;
	}
	public void setTenantId(int tenantId) {
		this.tenantId = tenantId;
	}
	
	public List<InstallationAllDto> getInstallations() {
		return installations;
	}
	public void setInstallations(List<InstallationAllDto> installations) {
		this.installations = installations;
	}
    
}
