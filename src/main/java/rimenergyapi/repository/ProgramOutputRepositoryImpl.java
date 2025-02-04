package rimenergyapi.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import rimenergyapi.entity.ProgramOutputEntity;

@Repository
public class ProgramOutputRepositoryImpl implements ProgramOutputRepositoryCustom {
	
	@Autowired
	@Qualifier("tricityEntityManager")
	private EntityManager em;

	@Override
	public List<ProgramOutputEntity> findAllByOutputIdRange(List<Integer> outputsId) {
		if (outputsId!= null & !outputsId.isEmpty()) {
			return  em.createQuery("SELECT p_o FROM ProgramOutputEntity p_o WHERE p_o.outputId IN (:outputsId)")
					.setParameter("outputsId", outputsId)
					.getResultList();
		}
		return new ArrayList<ProgramOutputEntity>();
	}

}
