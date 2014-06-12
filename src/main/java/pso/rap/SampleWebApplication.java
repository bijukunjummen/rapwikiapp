package pso.rap;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories
@ComponentScan
@ImportResource({"classpath:/META-INF/spring/pageaccess.xml", "classpath:/META-INF/spring/pageviewstats.xml"})
public class SampleWebApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SampleWebApplication.class, args);
	}


	@Value("${spring.datasource.url}")
	private String dataSourceUrl;

	@Value("${spring.driver.class}")
	private String driverClassName;

	@Bean
	@Profile("default")
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(dataSourceUrl);
		dataSource.setUsername("app");
		dataSource.setPassword("app");
		return dataSource;
	}

	@Bean
	@Profile("default")
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
	@Profile("default")
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return jpaTransactionManager;
	}
}
