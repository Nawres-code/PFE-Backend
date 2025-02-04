package rimenergyapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rimenergyapi.entity.UserEntity;

public interface AccountRepository extends JpaRepository<UserEntity, Long> {
	public UserEntity findByUsername(String username);
	
	
	@Query(value = "(select d.id_device from  user_vehicule uv, device d where uv.user_id=:idUser and d.vehicule_id=uv.vehicule_id) union (select d.id_device from  user_group ug, vehicule_groupe vg, device d  where ug.user_id= :idUser and ug.group_id=vg.groupe_id and vg.vehicule_id=d.vehicule_id)", nativeQuery = true)
	public List<Long> getAllDevicesOfUser(@Param("idUser") long idUser);
}
