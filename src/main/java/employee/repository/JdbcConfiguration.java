package employee.repository;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@EnableTransactionManagement
public class JdbcConfiguration {
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {

		BoneCPDataSource dataSource = new BoneCPDataSource();
		dataSource.setDriverClass("org.postgresql.Driver");
		dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/book");
		dataSource.setUsername("postgres");
		dataSource.setPassword("guyard59");
		dataSource.setIdleConnectionTestPeriodInMinutes(10);
		dataSource.setIdleMaxAgeInMinutes(10);
		dataSource.setMaxConnectionsPerPartition(4);
		dataSource.setMinConnectionsPerPartition(2);
		dataSource.setPartitionCount(2);
		dataSource.setAcquireIncrement(10);
		dataSource.setStatementsCacheSize(10);
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}
}
