package rimenergyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rimenergyapi.entity.SensorEntity;

public interface SensorRepository extends JpaRepository<SensorEntity, String>{

}
