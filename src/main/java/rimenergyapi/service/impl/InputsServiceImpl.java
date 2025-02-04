package rimenergyapi.service.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.dto.InputCategoryInfoDto;
import rimenergyapi.dto.InputInfoDto;
import rimenergyapi.entity.InputCategoryEntity;
import rimenergyapi.entity.InputEntity;
import rimenergyapi.repository.InputCategoryRepository;
import rimenergyapi.repository.InputRepository;
import rimenergyapi.service.InputsService;

@Service
public class InputsServiceImpl implements InputsService {

	@Autowired
	private InputRepository inputRepo;
	@Autowired
	private InputCategoryRepository catRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Transactional
	@Override
	public InputInfoDto updateInputName(InputInfoDto input) {
		InputEntity entity = inputRepo.findById(input.getId()).orElseGet(null);
		if(entity != null) {
			entity.setName(input.getName()); 
			entity = inputRepo.save(entity);
			return modelMapper.map(entity, InputInfoDto.class);
		}
		return null;
	}
	
	@Transactional
	@Override
	public InputCategoryInfoDto updateCatInputName(InputCategoryInfoDto cat) {
		InputCategoryEntity entity = catRepo.findById(cat.getId()).orElseGet(null);
		if(entity != null) {
			entity.setName(cat.getName()); 
			entity = catRepo.save(entity);
			return modelMapper.map(entity, InputCategoryInfoDto.class);
		}
		return null;
	}

}
