package rimenergyapi.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rimenergyapi.repository.OutputRepository;
import rimenergyapi.service.OutputService;

@Service
public class OutputServiceImpl implements OutputService{

	@Autowired
	private OutputRepository outputRepo;

	@Transactional
	@Override
	public boolean updateOutputStatus(int id, boolean oldStatus) {
		int rowCount = 0;
		// NB: never mind! it will be updated by the output_collector
		rowCount = outputRepo.updateOutputStatus(id, !oldStatus);
		if(rowCount == 1) { return !oldStatus; }
		return oldStatus;
	}
	
	
}
