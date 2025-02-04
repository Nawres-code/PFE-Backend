package rimenergyapi.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rimenergyapi.entity.ActionEntity;
import rimenergyapi.entity.UserEntity;

public interface ActionRepository extends JpaRepository<ActionEntity, Long>{

	public List<ActionEntity> findByUserAndCreatedAtBetweenOrderByCreatedAtDesc(UserEntity user,
			Timestamp startDate, Timestamp endDate);
}
