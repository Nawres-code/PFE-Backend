package rimenergyapi.config;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywaySlaveInitializerConfig {

	@Value("${tenant_i.datasource.url}")
	private String tenantIUrl;

	@Value("${tenant_i.datasource.username}")
	private String tenantIUsername;

	@Value("${tenant_i.datasource.password}")
	private String tenantIPassword;

	@Value("${tenant_i.datasource.driverClassName}")
	private String tenantIDriverClassName;

	@PostConstruct
	public void migrateFlyway() {
		Arrays.asList(16).forEach(tenantId -> {
			Flyway flyway = new Flyway();
			flyway.setDataSource(tenantIUrl + tenantId, tenantIUsername, tenantIPassword);
			flyway.setLocations("db/migration");
			flyway.setBaselineOnMigrate(true);
			flyway.baseline();
			flyway.migrate();
			flyway = new Flyway();
		});
	}
}