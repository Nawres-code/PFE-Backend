package rimenergyapi.service.impl;


import java.util.List;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rimenergyapi.entity.ProgramEntity;
import rimenergyapi.entity.ProgramOutputEntity;
import rimenergyapi.repository.ProgramOutputRepository;
import rimenergyapi.repository.ProgramRepository;
import rimenergyapi.service.ProgramService;

@Service
public class ProgramServiceImpl implements ProgramService {

	@Autowired
	private ProgramRepository progRepo;
	
	@Autowired
	private ProgramOutputRepository programOutputRepo;

	@Autowired
	private CurrentTenantIdentifierResolver tenantIdentifier;

	@Override
	public List<ProgramEntity> getAllProgramsByCurrentUser() {
		try {
			return progRepo.findAllByTenantId(Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(transactionManager = "tricityTransactionManager")
	public ProgramEntity saveProgram(ProgramEntity program) {
		try {
			if (program.getTenantId() < 1) {
				program.setTenantId(Integer.parseInt(tenantIdentifier.resolveCurrentTenantIdentifier()));
			}
			if(program.getId() == -1) {
				program.setId(0);
			}
			return progRepo.save(program);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	@Transactional(transactionManager = "tricityTransactionManager")
	public void deleteProgram(int programId) {
		try {	
//			try {
//				programOutputRepo.removeByProgramId(programId);
//			}catch(Exception e) {
//				e.printStackTrace();
//				return false;
//			}
			progRepo.deleteById(programId);
		} catch(Exception e) {
			e.printStackTrace();
			// return false;
		}
		// return true;
	}

}
