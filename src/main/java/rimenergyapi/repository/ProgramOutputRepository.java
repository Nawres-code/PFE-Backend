package rimenergyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rimenergyapi.entity.ProgramOutputEntity;
import rimenergyapi.entity.ProgramOutputID;

public interface ProgramOutputRepository extends JpaRepository<ProgramOutputEntity, ProgramOutputID>, ProgramOutputRepositoryCustom {
	long removeByProgramId(int programId);
	List<ProgramOutputEntity> findAllByOutputId(int outputId);
	long removeByOutputId(int outputId);
}
