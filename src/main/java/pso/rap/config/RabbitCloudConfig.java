package pso.rap.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("rabbit-cloud")
@Configuration
public class RabbitCloudConfig extends AbstractCloudConfig {

	@Bean
	public ConnectionFactory rabbitConnectionFactory() {
		return connectionFactory().rabbitConnectionFactory();
	}
}


