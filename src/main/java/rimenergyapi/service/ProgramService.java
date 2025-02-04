package rimenergyapi.service;

import java.util.List;

import rimenergyapi.entity.ProgramEntity;

public interface ProgramService {

	List<ProgramEntity> getAllProgramsByCurrentUser();

	ProgramEntity saveProgram(ProgramEntity program);
	
	void deleteProgram(int idProg);

}
