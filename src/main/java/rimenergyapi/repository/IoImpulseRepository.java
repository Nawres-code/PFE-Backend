package rimenergyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rimenergyapi.entity.IOEntity;

public interface IoImpulseRepository extends JpaRepository<IOEntity, Integer> { 

	

}
