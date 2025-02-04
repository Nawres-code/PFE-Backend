package rimenergyapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import rimenergyapi.dto.CategoryInfoDto;
import rimenergyapi.service.CategoryService;

@RestController
@RequestMapping("/categories")
@CrossOrigin
@Api(value = "category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value="/{tenantId}", method = RequestMethod.GET)
	public @ResponseBody List<CategoryInfoDto> getAllByTenantId(@PathVariable("tenantId") int tenantId) {
		return categoryService.getAllByTenantId(tenantId);
	}
	
	@RequestMapping(value="/category", method = RequestMethod.POST)
	public @ResponseBody CategoryInfoDto createCategory(@RequestBody CategoryInfoDto categoryDto) {
		return categoryService.createCategory(categoryDto);
	}
	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
	public CategoryInfoDto updateCategory(@PathVariable("id") int id, @RequestBody CategoryInfoDto categoryDto) {
		categoryDto.setId(id);
		return categoryService.updateCategory(categoryDto);
	}
	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
	public boolean deleteCategory(@PathVariable("id") int id) {
		return categoryService.deleteCatgegory(id);
	}
}
