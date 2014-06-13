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



}
