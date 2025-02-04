package rimenergyapi.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.entity.IOEntity;
import rimenergyapi.repository.IoImpulseRepository;
import rimenergyapi.service.IoImpulseService;

@Service
public class IoImpulseServiceImpl implements IoImpulseService {

	@Autowired
	private IoImpulseRepository ioImpulseRepo;

	@Autowired
	protected ModelMapper mapper;
	
	
	@Transactional
	@Override
	public void renameIoImpulse(int id, String name) {
		try {
			Optional<IOEntity> entityOpt = ioImpulseRepo.findById(id);
			if(entityOpt.isPresent()) {
				IOEntity entityUpdate = entityOpt.get();
				entityUpdate.setName(name);
				ioImpulseRepo.saveAndFlush(entityUpdate);
			}
		} catch (Exception e) { }
	}


}
