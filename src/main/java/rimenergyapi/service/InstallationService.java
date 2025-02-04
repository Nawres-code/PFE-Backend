package rimenergyapi.service;

import java.util.List;

import rimenergyapi.dto.data.InstallationInfoDto;
import rimenergyapi.entity.InstallationView;

public interface InstallationService {

	public InstallationInfoDto addInstallation(InstallationInfoDto installationInfoDto);
	
	public InstallationInfoDto updateInstallation(InstallationInfoDto installationInfoDto);
	
	public Boolean deleteInstallation(int id);

	public List<InstallationInfoDto> getAllInstallation(int tenantId);
	
	Boolean toggleOutputMode(int idInstallation, boolean oldAutoMode);
	
	InstallationView getInstallationById( int installationId);
	
}
