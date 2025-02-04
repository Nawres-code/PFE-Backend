package rimenergyapi.service;

import java.util.List;

import rimenergyapi.dto.PhaseInfoDto;

public interface PhaseManagementService {

	List<PhaseInfoDto> getAllByTenantId(int tenantId);

	PhaseInfoDto updatePhase(PhaseInfoDto phaseDto);

	boolean deletePhase(int id);
	
	PhaseInfoDto addPhase(PhaseInfoDto phaseDto);
	
	

}
