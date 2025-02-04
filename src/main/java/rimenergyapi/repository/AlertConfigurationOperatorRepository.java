package rimenergyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rimenergyapi.entity.AlertConfigurationOperatorEntity;

public interface AlertConfigurationOperatorRepository extends JpaRepository<AlertConfigurationOperatorEntity, Integer>{
	AlertConfigurationOperatorEntity findByName(String name);
}
