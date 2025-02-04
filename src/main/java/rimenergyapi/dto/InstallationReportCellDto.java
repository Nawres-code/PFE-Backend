package rimenergyapi.dto;


public class InstallationReportCellDto {
	private Long 	idInstallation;
	private Long	code;
	private String	magasin;
	private Long	threshold;
	private String	name;
	private String	type;
	private Double	value;
	private boolean isOpen;
	
	public Long getIdInstallation() {
		return idInstallation;
	}
	
	public void setIdInstallation(Long idInstallation) {
		this.idInstallation = idInstallation;
	}
	
	public boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getMagasin() {
		return magasin;
	}
	public void setMagasin(String magasin) {
		this.magasin = magasin;
	}
	public Long getThreshold() {
		return threshold;
	}
	public void setThreshold(Long threshold) {
		this.threshold = threshold;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((idInstallation == null) ? 0 : idInstallation.hashCode());
		result = prime * result + (isOpen ? 1231 : 1237);
		result = prime * result + ((magasin == null) ? 0 : magasin.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((threshold == null) ? 0 : threshold.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InstallationReportCellDto other = (InstallationReportCellDto) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (idInstallation == null) {
			if (other.idInstallation != null)
				return false;
		} else if (!idInstallation.equals(other.idInstallation))
			return false;
		if (isOpen != other.isOpen)
			return false;
		if (magasin == null) {
			if (other.magasin != null)
				return false;
		} else if (!magasin.equals(other.magasin))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (threshold == null) {
			if (other.threshold != null)
				return false;
		} else if (!threshold.equals(other.threshold))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
	
	
	
	
}

