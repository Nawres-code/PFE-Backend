package rimenergyapi.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * TenantDatasourceConfig.
 *
 * @author Amine HN
 */
@Configuration
@EnableConfigurationProperties(JpaProperties.class)
@EnableJpaRepositories(
		entityManagerFactoryRef = "masterEntityManager",
		transactionManagerRef = "masterTransactionManager",
		basePackages = {"rimenergyapi.security.repository"},
		enableDefaultTransactions = false)
@EnableTransactionManagement
public class TenantDatasourceConfig {

	// load tenant (master) datasource config !
	@Value("${tenant.datasource.url}")
	private String tenantUrl;

	@Value("${tenant.datasource.username}")
	private String tenantUsername;

	@Value("${tenant.datasource.password}")
	private String tenantPassword;

	@Value("${tenant.datasource.driverClassName}")
	private String tenantDriverClassName;

	@Value("${tenant.datasource.minIdle}")
	private int tenantMinIdle;

	@Value("${tenant.datasource.maxIdle}")
	private int tenantMaxIdle;

	@Value("${tenant.datasource.maxActive}")
	private int tenantMaxActive;

	@Bean(destroyMethod = "close")
	public DataSource userdataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(tenantDriverClassName);
		dataSource.setUrl(tenantUrl);
		dataSource.setUsername(tenantUsername);
		dataSource.setPassword(tenantPassword);
		dataSource.setMaxIdle(tenantMinIdle);
		dataSource.setMaxIdle(tenantMaxIdle);
		dataSource.setMaxActive(tenantMaxActive);
		dataSource.setValidationQuery("SELECT 1 ");
		dataSource.setMaxWait(6000);
		dataSource.setRemoveAbandoned(true);
		dataSource.setLogAbandoned(true);
		dataSource.setTestOnBorrow(true);
		dataSource.setTestOnReturn(false);
		dataSource.setTestWhileIdle(false);
		dataSource.setTimeBetweenEvictionRunsMillis(30000);
		dataSource.setMinEvictableIdleTimeMillis(30000);
		return dataSource;
	}

	@Bean(name = "masterEntityManager")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(userdataSource());
		em.setPackagesToScan(new String[] { "rimenergyapi.security.entity" });
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalJpaProperties());
		em.setPersistenceUnitName("master");
		return em;
	}

	private Properties additionalJpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
		properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
		return properties;
	}

	@Bean(name = "masterTransactionManager")
	public JpaTransactionManager masterTransactionManager(
			@Qualifier("masterEntityManager") EntityManagerFactory masterEntityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(masterEntityManager);
		return transactionManager;
	}
}
