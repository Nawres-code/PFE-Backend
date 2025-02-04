package rimenergyapi.service;

import rimenergyapi.dto.AuthorityInfoDto;
import rimenergyapi.dto.CategoryInfoDto;
import rimenergyapi.dto.InstallationAllDto;
import rimenergyapi.dto.ProviderInstallationAllDto;
import rimenergyapi.dto.ZonesInfoDto;
import rimenergyapi.entity.CategoryEntity;
import rimenergyapi.entity.ProviderInstallationEntity;
import rimenergyapi.entity.ZonesEntity;

import java.util.List;

public interface DataManagmentService {
    public List<ZonesInfoDto> getAll();
    
    public List<CategoryInfoDto> getAllCategory(int tenantId);
    
    public List<ZonesInfoDto> getAllZonesByTenantId( int tenantId);
    
    public List<AuthorityInfoDto> getAllAuthority();
}
