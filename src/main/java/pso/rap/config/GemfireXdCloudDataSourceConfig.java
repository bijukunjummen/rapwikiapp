package pso.rap.config;

import com.jayway.jsonpath.JsonPath;
import com.pivotal.gemfirexd.hibernate.GemFireXDDialect;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.List;
import java.util.Properties;

@Profile("gemfirexd-cloud")
@Configuration
public class GemfireXdCloudDataSourceConfig extends AbstractJpaRepositoryConfig{


	@Bean
	public DataSource dataSource() {
		String vcapServices = System.getenv("VCAP_SERVICES");
		List<String> connectionList = JsonPath.read(vcapServices, "$..gemfirexd.uri");
		String connectionString = connectionList.get(0);
		ConnectionInfo connectionInfo = parseConnectionString(connectionString);
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.pivotal.gemfirexd.jdbc.ClientDriver");
		dataSource.setUrl(connectionInfo.uri);
		dataSource.setUsername(connectionInfo.userName);
		dataSource.setPassword(connectionInfo.password);
		return dataSource;
	}
	protected String getPackagesToScan() {
		return "pso.rap.domain";
	}

	@Bean
	public JpaVendorAdapter getJpaVendorAdapter() {
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.DERBY);
		jpaVendorAdapter.setDatabasePlatform(GemFireXDDialect.class.getName());
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setShowSql(false);
		return jpaVendorAdapter;
	}

	protected Properties getAdditionalProperties() {
		Properties properties = new Properties();
		properties.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "update");
		return properties;
	}

//	private Properties additionalProperties() {
//		Properties properties = new Properties();
//		properties.setProperty("hibernate.hbm2ddl.auto", "update");
//		properties.setProperty("hibernate.dialect", "com.pivotal.gemfirexd.hibernate.GemFireXDDialect");
//		return properties;
//	}

	private ConnectionInfo parseConnectionString(String s) {
		ConnectionInfo connectionInfo = new ConnectionInfo();
		String[] tokens = s.split(";");
		connectionInfo.uri = tokens[0];
		connectionInfo.userName = tokens[1].split("=")[1];
		connectionInfo.password = tokens[2].split("=")[1];
		return connectionInfo;
	}

	class ConnectionInfo {
		String userName;
		String password;
		String uri;
	}
}


