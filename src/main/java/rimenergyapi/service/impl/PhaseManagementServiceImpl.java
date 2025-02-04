package rimenergyapi.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.dto.PhaseInfoDto;
import rimenergyapi.repository.PhaseRepository;
import rimenergyapi.service.PhaseManagementService;
import rimenergyapi.utils.GenericUtil;

@Service
public class PhaseManagementServiceImpl implements PhaseManagementService {
	
	@Autowired
	private PhaseRepository phaseRepo;
	
	@Autowired
	protected ModelMapper mapper;

	@Override
	public List<PhaseInfoDto> getAllByTenantId(int tenantId) {
		try {
			return GenericUtil.map(mapper, phaseRepo.findByDevice_TenantId(tenantId), PhaseInfoDto.class );
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public PhaseInfoDto updatePhase(PhaseInfoDto phaseDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deletePhase(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public PhaseInfoDto addPhase(PhaseInfoDto phaseDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
