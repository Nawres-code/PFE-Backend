package rimenergyapi.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import rimenergyapi.DataSourceBasedMultiTenantConnectionProviderImpl;

@Configuration
public class MultitenancyConfig {

	private static final String DEFAULT_TENANT_ID = "tenant_1";

	@Bean(name = "multitenantProvider")
	public DataSourceBasedMultiTenantConnectionProviderImpl dataSourceBasedMultiTenantConnectionProvider() {
		HashMap<String, DataSource> dataSources = new HashMap<String, DataSource>();
		return new DataSourceBasedMultiTenantConnectionProviderImpl(DEFAULT_TENANT_ID,
				dataSources);
	}

	@Bean
	@DependsOn("multitenantProvider")
	public DataSource defaultDataSource() {
		return dataSourceBasedMultiTenantConnectionProvider().getDefaultDataSource();
	}

}
