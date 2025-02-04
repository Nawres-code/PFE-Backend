package rimenergyapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rimenergyapi.entity.CategoryEntity;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

	
	List<CategoryEntity> findAllByTenantId(@Param("tenantId") int tenantId, Sort sort);
	
	@Query(value = " SELECT * FROM category c"
			+ " where c.tenant_id = :tenantId", nativeQuery = true)
	List<CategoryEntity> getAllCtegory(@Param("tenantId") int tenantId);
	

	@Query(value = " SELECT * FROM category c"
			+ " where c.tenant_id = :tenantId and c.type not like 'provider'", nativeQuery = true)
	List<CategoryEntity> getAllConsumptionCtegory(@Param("tenantId") int tenantId);
	
	List<CategoryEntity> findByTenantId(int tenantId);

}
