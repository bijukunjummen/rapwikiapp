package pso.rap.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
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

import javax.sql.DataSource;
import java.util.Properties;

@Profile("cloud")
@Configuration
public class CloudRabbitConfig extends AbstractCloudConfig {

	@Bean
	public ConnectionFactory rabbitConnectionFactory() {
		return connectionFactory().rabbitConnectionFactory("rabbitmq-service");
	}
}


