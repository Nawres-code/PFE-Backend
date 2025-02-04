package rimenergyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rimenergyapi.entity.StationEntity;

public interface StationRepository extends JpaRepository<StationEntity, String>{

}
