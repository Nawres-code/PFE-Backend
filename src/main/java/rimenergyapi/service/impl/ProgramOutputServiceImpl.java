package rimenergyapi.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.entity.ProgramOutputEntity;
import rimenergyapi.repository.ProgramOutputRepository;
import rimenergyapi.service.ProgramOutputService;

@Service
public class ProgramOutputServiceImpl implements ProgramOutputService {
	
	@Autowired
	private ProgramOutputRepository programOutputRepository;

	@Transactional
	@Override
	public void updateOutputPrograms(int idOutput, List<Integer> programs) {
		programOutputRepository.removeByOutputId(idOutput);
		ProgramOutputEntity entity = new ProgramOutputEntity();
		entity.setOutputId(idOutput);
		for(int idProg : programs) {
			entity.setProgramId(idProg);
			programOutputRepository.save(entity);
		}
	}

}
