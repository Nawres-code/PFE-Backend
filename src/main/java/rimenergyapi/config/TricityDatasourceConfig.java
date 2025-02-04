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
import org.springframework.context.annotation.Primary;
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
@Primary
@EnableConfigurationProperties(JpaProperties.class)
@EnableJpaRepositories(
		entityManagerFactoryRef = "tricityEntityManager", 
		transactionManagerRef = "tricityTransactionManager",
		basePackages = {"rimenergyapi.repository" }, enableDefaultTransactions = false)
@EnableTransactionManagement
public class TricityDatasourceConfig {

	// load tricity (master) datasource config !
	@Value("${tricity.datasource.url}")
	private String tricityUrl;

	@Value("${tricity.datasource.username}")
	private String tricityUsername;

	@Value("${tricity.datasource.password}")
	private String tricityPassword;

	@Value("${tricity.datasource.driverClassName}")
	private String tricityDriverClassName;

	@Value("${tricity.datasource.minIdle}")
	private int tricityMinIdle;

	@Value("${tricity.datasource.maxIdle}")
	private int tricityMaxIdle;

	@Value("${tricity.datasource.maxActive}")
	private int tricityMaxActive;

	@Primary
	@Bean(destroyMethod = "close")
	public DataSource tricitydataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(tricityDriverClassName);
		dataSource.setUrl(tricityUrl);
		dataSource.setUsername(tricityUsername);
		dataSource.setPassword(tricityPassword);
		dataSource.setMaxIdle(tricityMinIdle);
		dataSource.setMaxIdle(tricityMaxIdle);
		dataSource.setMaxActive(tricityMaxActive);
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

	@Primary
	@Bean(name = "tricityEntityManager")
	public LocalContainerEntityManagerFactoryBean tricityManagerFactory() {
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(tricitydataSource());
		em.setPackagesToScan(new String[] { "rimenergyapi.entity" });
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalJpaProperties());
		em.setPersistenceUnitName("tricity");
		return em;
	}

	private Properties additionalJpaProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.physical_naming_strategy", SpringPhysicalNamingStrategy.class.getName());
		properties.put("hibernate.implicit_naming_strategy", SpringImplicitNamingStrategy.class.getName());
	  //  properties.put("hibernate.jdbc.batch_size", "5");
		return properties;
	}
	
	@Primary
	@Bean(name = "tricityTransactionManager")
	public JpaTransactionManager tricityTransactionManager(
			@Qualifier("tricityEntityManager") EntityManagerFactory tricityEntityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(tricityEntityManager);
		return transactionManager;
	}
}
