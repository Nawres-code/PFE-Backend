package rimenergyapi.repository;

import java.util.List;

import rimenergyapi.entity.ProgramOutputEntity;

public interface ProgramOutputRepositoryCustom {
	List<ProgramOutputEntity> findAllByOutputIdRange(List<Integer> outputsId);

}
