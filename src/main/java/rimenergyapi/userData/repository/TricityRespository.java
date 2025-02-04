package rimenergyapi.userData.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import rimenergyapi.userData.model.RtTricityDto;

@Repository
public class TricityRespository {
	@Autowired
	@Qualifier("tenantEntityManager")
	private EntityManager em;
	
	  public List<RtTricityDto> findAllRtTricity(){
	    	List<RtTricityDto> rtTricity = new ArrayList<RtTricityDto>();
	    	String query = "SELECT * FROM rt_energy";
	    	try {
	    		rtTricity = em.createNativeQuery(query).getResultList();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	return rtTricity;
	    }
	  
	    public List<RtTricityDto> findListRtTricity(){
	    	List<RtTricityDto> rtTricity = new ArrayList<RtTricityDto>();
	    	String query = "SELECT * FROM rt_tricity tr, groups_phase gp, installation it, groups g " + 
	    			"where tr.phase_id = gp.phase_id and gp.groups_id = g.id and g.installation_id = it.id and it.id in :installationIds";
	    	try {
	    		rtTricity = em.createNativeQuery(query).getResultList();
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    	}
	    	return rtTricity;
	    }
}
