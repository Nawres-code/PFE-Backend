package rimenergyapi.dto;

import java.util.List;

import rimenergyapi.dto.data.SondeInfoDto;

public class DeviceInfoDto {

	    private int id;
	    private int nbPhase;
	    private byte enabled;
	    private int lastId;
	    private int tenantId;
	    private String protocol;
	    private String type;
	    private String pool;
	    private Byte isFictif;
	    private String name;
	    private int period;
	    private int installationId;
	    private List<SondeInfoDto> sondes;
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getNbPhase() {
			return nbPhase;
		}
		public void setNbPhase(int nbPhase) {
			this.nbPhase = nbPhase;
		}
		public byte getEnabled() {
			return enabled;
		}
		public void setEnabled(byte enabled) {
			this.enabled = enabled;
		}
		public int getLastId() {
			return lastId;
		}
		public void setLastId(int lastId) {
			this.lastId = lastId;
		}
		public int getTenantId() {
			return tenantId;
		}
		public void setTenantId(int tenantId) {
			this.tenantId = tenantId;
		}
		public String getProtocol() {
			return protocol;
		}
		public void setProtocol(String protocol) {
			this.protocol = protocol;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public String getPool() {
			return pool;
		}
		public void setPool(String pool) {
			this.pool = pool;
		}
		public Byte getIsFictif() {
			return isFictif;
		}
		public void setIsFictif(Byte isFictif) {
			this.isFictif = isFictif;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getPeriod() {
			return period;
		}
		public void setPeriod(int period) {
			this.period = period;
		}
		public List<SondeInfoDto> getSondes() {
			return sondes;
		}
		public void setSondes(List<SondeInfoDto> sondes) {
			this.sondes = sondes;
		}
		public int getInstallationId() {
			return installationId;
		}
		public void setInstallationId(int installationId) {
			this.installationId = installationId;
		}
}
