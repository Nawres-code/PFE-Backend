package rimenergyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rimenergyapi.entity.OutputEntity;

public interface OutputRepository extends JpaRepository<OutputEntity, Integer> {
	// false 
	@Modifying
	@Query(nativeQuery = true, value = "UPDATE output SET status = :status WHERE id = :id")
	int updateOutputStatus(@Param("id") int id, @Param("status") boolean status);
}
