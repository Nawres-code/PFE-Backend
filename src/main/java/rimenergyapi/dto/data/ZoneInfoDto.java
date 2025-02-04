package rimenergyapi.dto.data;

public class ZoneInfoDto {
	private int idZone;
	private String name;
	private int tenantId;

	public ZoneInfoDto() {
	}

	public ZoneInfoDto(int idZone, String name) {
		this.idZone = idZone;
		this.name = name;
	}

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
	

}
