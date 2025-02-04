package rimenergyapi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import rimenergyapi.dto.AuthorityInfoDto;
import rimenergyapi.dto.CategoryInfoDto;
import rimenergyapi.dto.ZonesInfoDto;
import rimenergyapi.entity.CategoryEntity;
import rimenergyapi.entity.ZonesEntity;
import rimenergyapi.repository.AuthorityRepository;
import rimenergyapi.repository.CategoryRepository;
import rimenergyapi.repository.InstallationRepository;
import rimenergyapi.repository.ProviderInstallationEntityRepository;
import rimenergyapi.repository.ZonesRepository;
import rimenergyapi.service.DataManagmentService;
import rimenergyapi.utils.GenericUtil;

@Service
public class DataManagmentServiceImp implements DataManagmentService {
    @Autowired
    private ZonesRepository zonesRepository;
    
    @Autowired
    private ProviderInstallationEntityRepository providerRepo;

    @Autowired
    private InstallationRepository instRepo;
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Autowired
    private AuthorityRepository authorityRepository;
    
	@Autowired
	protected ModelMapper mapper;

    @Override
    public List<ZonesInfoDto> getAll() {
    	List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Sort.Direction.ASC, "name"));
        return GenericUtil.map(mapper, zonesRepository.findAll(Sort.by(orders)), ZonesInfoDto.class);
    }

	@Override
	public List<CategoryInfoDto> getAllCategory(int tenantId) {
		// TODO Auto-generated method stub
		List<Order> orders = new ArrayList<Order>();
		orders.add(new Order(Sort.Direction.ASC, "name"));
		List<CategoryEntity> category = categoryRepository.findAllByTenantId(tenantId, Sort.by(orders));
		// category.add(new CategoryEntity(4, "Autres", "#FF0000", ""));
		return GenericUtil.map(mapper,category , CategoryInfoDto.class);
	}

	@Override
	public List<ZonesInfoDto> getAllZonesByTenantId(int tenantId) {
		List<ZonesEntity> zone = zonesRepository.getZoneByTenantIdOrderByNameAsc(tenantId);
		return GenericUtil.map(mapper, zone, ZonesInfoDto.class);
	}

	@Override
	public List<AuthorityInfoDto> getAllAuthority() {
		// TODO Auto-generated method stub
		   return GenericUtil.map(mapper, authorityRepository.findAll(), AuthorityInfoDto.class);
	}
	
}
