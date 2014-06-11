package pso.rap.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import java.util.Properties;

@Profile("cloud")
@Configuration
public class CloudDataSourceConfig{

	@Value("${vcap.services.gemfirexd-service.credentials.gemfirexd.uri}")
	private String connectionString;

	@Value("${spring.driver.class}")
	private String driverClassName;

	@Bean
	public DataSource dataSource() {
		ConnectionInfo connectionInfo = parseConnectionString(connectionString);
		System.out.println("*****CONNECTION STRING****" + connectionInfo.uri + ":" + connectionInfo.userName + ":" + connectionInfo.password);
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(connectionInfo.uri);
		dataSource.setUsername(connectionInfo.userName);
		dataSource.setPassword(connectionInfo.password);
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPackagesToScan("pso.rap.domain");
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactoryBean.setJpaProperties(additionalProperties());
		return entityManagerFactoryBean;
	}

	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "com.pivotal.gemfirexd.hibernate.GemFireXDDialect");
		return properties;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return jpaTransactionManager;
	}

	private ConnectionInfo parseConnectionString(String s) {
		ConnectionInfo connectionInfo = new ConnectionInfo();
		String[] tokens = s.split(";") ;
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


