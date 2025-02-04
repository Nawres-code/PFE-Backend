package rimenergyapi.config;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.Environment;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import rimenergyapi.entity.DeviceEntity;

@Configuration
@EnableConfigurationProperties(JpaProperties.class)
@ComponentScan("rimenergyapi")
@EnableJpaRepositories(entityManagerFactoryRef = "tenantEntityManager", transactionManagerRef = "tenantTransactionManager",
basePackages = {"rimenergyapi.archive.repository"}, enableDefaultTransactions = false)
@EnableTransactionManagement
public class MultiTenancyJpaConfig {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private MultiTenantConnectionProvider multiTenantConnectionProvider;

	@Autowired
	private CurrentTenantIdentifierResolver currentTenantIdentifierResolver;

	@Bean(name = "tenantEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPackagesToScan("rimenergyapi.archive.model");
		entityManagerFactoryBean.setJpaPropertyMap(getHibernateProps());
		entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

		entityManagerFactoryBean.setJpaProperties(additionalJpaProperties());
		// additional config of factory
		return entityManagerFactoryBean;
	}
	
	
	private Map<String, Object> getHibernateProps(){
		Map<String, Object> hibernateProps = new LinkedHashMap<>();

		hibernateProps.put(Environment.MULTI_TENANT, MultiTenancyStrategy.DATABASE);
		hibernateProps.put(Environment.MULTI_TENANT_CONNECTION_PROVIDER, multiTenantConnectionProvider);
		hibernateProps.put(Environment.MULTI_TENANT_IDENTIFIER_RESOLVER, currentTenantIdentifierResolver);
		hibernateProps.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

		hibernateProps.put(Environment.POOL_SIZE, 30);

		hibernateProps.put(Environment.C3P0_ACQUIRE_INCREMENT, 5);
		hibernateProps.put(Environment.C3P0_IDLE_TEST_PERIOD, 3000);
		hibernateProps.put(Environment.C3P0_MAX_SIZE, 150);
		hibernateProps.put(Environment.C3P0_MIN_SIZE, 30);
		hibernateProps.put(Environment.C3P0_MAX_STATEMENTS, 10);
		hibernateProps.put(Environment.C3P0_TIMEOUT, 2500);
		hibernateProps.put(Environment.SHOW_SQL, true);

		hibernateProps.put("hibernate.dbcp.maxIdle", 50);
		hibernateProps.put("hibernate.dbcp.initialSize", 8);
		hibernateProps.put("hibernate.dbcp.minIdle", 30);

		hibernateProps.put("hibernate.dbcp.maxActive", 20);
		hibernateProps.put("hibernate.dbcp.whenExhaustedAction", 1);
		hibernateProps.put("hibernate.dbcp.maxWait", 20000);
		hibernateProps.put("hibernate.dbcp.maxWait", 20000);
		hibernateProps.put("hibernate.dbcp.validationQuery", "select 1");
		hibernateProps.put("hibernate.dbcp.testOnBorrow", true);
		hibernateProps.put("hibernate.dbcp.testOnReturn", true);

		hibernateProps.put("spring.datasource.test-on-borrow", true);
		hibernateProps.put("spring.datasource.validation-query", "SELECT 1");
		return hibernateProps;
	}
	
	private Properties additionalJpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
		properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
		return properties;
	}
	

	@Bean(name = "tenantTransactionManager")
	public JpaTransactionManager masterTransactionManager(
			@Qualifier("tenantEntityManager") EntityManagerFactory tenantEntityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(tenantEntityManager);
		return transactionManager;
	}

}
