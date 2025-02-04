package rimenergyapi.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.dto.CategoryInfoDto;
import rimenergyapi.entity.CategoryEntity;
import rimenergyapi.repository.CategoryRepository;
import rimenergyapi.service.CategoryService;
import rimenergyapi.utils.GenericUtil;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	protected ModelMapper mapper;

	@Override
	public List<CategoryInfoDto> getAllByTenantId(int tenantId) {
		try {
			List<CategoryEntity> list = categoryRepo.findByTenantId(tenantId);
			return GenericUtil.map(mapper,
					list, CategoryInfoDto.class);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public CategoryInfoDto createCategory(CategoryInfoDto categoryDto) {
		try {
			CategoryEntity category =  mapper.map(categoryDto, CategoryEntity.class); 
			category = categoryRepo.save(category);
			categoryDto = mapper.map(category, CategoryInfoDto.class);
			return categoryDto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public CategoryInfoDto updateCategory(CategoryInfoDto categoryDto) {
		try {
			Optional<CategoryEntity> entityOpt = categoryRepo.findById((categoryDto.getId()));
			if(entityOpt.isPresent()) {
				return createCategory(categoryDto);
			}
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return null;
	}

	@Override
	@Transactional
	public boolean deleteCatgegory(int id) {
		try {
			CategoryEntity entity = categoryRepo.findById(id).get();
			categoryRepo.delete(entity);
			return true;
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return false;
	}

}
