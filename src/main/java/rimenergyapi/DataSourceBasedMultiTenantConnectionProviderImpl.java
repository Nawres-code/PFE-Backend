package rimenergyapi;

import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Value;

public class DataSourceBasedMultiTenantConnectionProviderImpl
		extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

	private static final long serialVersionUID = 1L;

	private String defaultTenant;

	// datasource ram db
	private Map<String, DataSource> datasourceDb;

	// load tenant (master) datasource config !
	@Value("${tenant_i.datasource.url}")
	private String tenantIUrl;

	@Value("${tenant_i.datasource.username}")
	private String tenantIUsername;

	@Value("${tenant_i.datasource.password}")
	private String tenantIPassword;

	@Value("${tenant_i.datasource.driverClassName}")
	private String tenantIDriverClassName;

	@Value("${tenant_i.datasource.minIdle}")
	private int tenantIMinIdle;

	@Value("${tenant_i.datasource.maxIdle}")
	private int tenantIMaxIdle;

	@Value("${tenant_i.datasource.maxActive}")
	private int tenantIMaxActive;

	public DataSourceBasedMultiTenantConnectionProviderImpl(String defaultTenant, Map<String, DataSource> map) {
		super();
		this.defaultTenant = defaultTenant;
		this.datasourceDb = map;
	}

	@Override
	protected DataSource selectAnyDataSource() {
		return datasourceDb.get(defaultTenant);
	}

	@Override
	protected DataSource selectDataSource(String tenantIdentifier) {
		return datasourceDb.get(tenantIdentifier);
	}

	public void addDataSource(String tenant_id, DataSource dataSource) {
		if (!datasourceDb.containsKey(tenant_id)) {
			this.datasourceDb.put(tenant_id, dataSource);
		}
	}

	public void addDataSource(String tenant_id) {
		if (!datasourceDb.containsKey(tenant_id)) {
			BasicDataSource basicDataSource = new BasicDataSource();

			basicDataSource.setUrl(tenantIUrl + tenant_id);
			basicDataSource.setUsername(tenantIUsername);
			basicDataSource.setPassword(tenantIPassword);
			basicDataSource.setDriverClassName(tenantIDriverClassName);
			basicDataSource.setValidationQuery("SELECT 1 ");
			basicDataSource.setMinIdle(tenantIMinIdle);
			basicDataSource.setMaxIdle(tenantIMaxIdle);
			basicDataSource.setMaxActive(tenantIMaxActive);
			basicDataSource.setMaxWait(6000);
			basicDataSource.setRemoveAbandoned(true);
			basicDataSource.setLogAbandoned(true);
			basicDataSource.setTestOnBorrow(true);
			basicDataSource.setTestOnReturn(false);
			basicDataSource.setTestWhileIdle(false);
			basicDataSource.setTimeBetweenEvictionRunsMillis(30000);
			basicDataSource.setMinEvictableIdleTimeMillis(30000);
			basicDataSource.setDefaultAutoCommit(false);

			this.datasourceDb.put(tenant_id, basicDataSource);
		}
		
	}

	public boolean containTenant(String tenant_id) {
		return datasourceDb.containsKey(tenant_id);
	}

	public Object deleteDataSource(String idTenant) throws SQLException {
		this.releaseConnection(idTenant, this.datasourceDb.get(idTenant).getConnection());
		return this.datasourceDb.remove(idTenant);
	}

	public void deleteAllDataSource() {
		this.datasourceDb.clear();
	}

	public DataSource getDefaultDataSource() {
		return datasourceDb.get(defaultTenant);
	}

	public String getDefaultTenant() {
		return defaultTenant;
	}

	public void setDefaultTenant(String defaultTenant) {
		this.defaultTenant = defaultTenant;
	}

	public Map<String, DataSource> getDatasourceDb() {
		return datasourceDb;
	}

	public void setDatasourceDb(Map<String, DataSource> datasourceDb) {
		this.datasourceDb = datasourceDb;
	}

}
