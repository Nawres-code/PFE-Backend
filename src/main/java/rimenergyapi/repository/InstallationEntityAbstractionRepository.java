package rimenergyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import rimenergyapi.entity.InstallationView;

public interface InstallationEntityAbstractionRepository extends JpaRepository<InstallationView, Integer> {

}
