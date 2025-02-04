package rimenergyapi.userData.repository;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import rimenergyapi.userData.model.ArchiveDto;
import rimenergyapi.userData.model.ArchiveTemperatureDto;

@Repository
public class ArchiveRepository {

	@Autowired
	@Qualifier("tenantEntityManager")
	private EntityManager em;

	public List<ArchiveTemperatureDto> findListInTemperature(BigInteger sondeId, Timestamp startTime,
			Timestamp endTime) {
		List<ArchiveTemperatureDto> archiveTemperature = new ArrayList<ArchiveTemperatureDto>();
		String query = "SELECT * from  archive_temperature WHERE sonde_id =" + sondeId + " AND time BETWEEN "
				+ startTime + "AND " + endTime;
		try {
			archiveTemperature = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return archiveTemperature;
	}

	public List<ArchiveDto> findAllArchive() {
		List<ArchiveDto> archive = new ArrayList<ArchiveDto>();
		String query = "SELECT * from archive";
		try {
			archive = em.createNativeQuery(query).getResultList();// archiveJdbcTemplate.query(query, new
																	// BeanPropertyRowMapper<Archive>(Archive.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return archive;
	}

	public List<ArchiveTemperatureDto> findAllArchiveTemperature() {
		List<ArchiveTemperatureDto> archiveTemperature = new ArrayList<ArchiveTemperatureDto>();
		String query = "SELECT * from archive_temperature";
		try {
			archiveTemperature = em.createNativeQuery(query).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return archiveTemperature;
	}

}
