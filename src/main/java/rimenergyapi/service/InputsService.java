package rimenergyapi.service;

import rimenergyapi.dto.InputCategoryInfoDto;
import rimenergyapi.dto.InputInfoDto;

public interface InputsService {
	
	InputInfoDto updateInputName(InputInfoDto input);
	InputCategoryInfoDto updateCatInputName(InputCategoryInfoDto cat);


}
