package rimenergyapi.service;

import java.util.List;

import rimenergyapi.dto.CategoryInfoDto;

public interface CategoryService {

	List<CategoryInfoDto> getAllByTenantId(int tenantId);

	CategoryInfoDto createCategory(CategoryInfoDto categoryDto);

	CategoryInfoDto updateCategory(CategoryInfoDto categoryDto);

	boolean deleteCatgegory(int id);

}
