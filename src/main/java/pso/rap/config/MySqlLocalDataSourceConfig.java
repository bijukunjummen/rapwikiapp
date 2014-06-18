package pso.rap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("mysql-local")
@EnableConfigurationProperties({MySqlProperties.class})
public class MySqlLocalDataSourceConfig extends AbstractLocalDataSourceConfig {

    @Autowired
    private MySqlProperties mySqlProperties;

    @Bean
    public DataSource dataSource() {
        return createBasicDataSource(mySqlProperties.getUrl(), mySqlProperties.getDriver(), mySqlProperties.getUsername(), mySqlProperties.getPassword());
    }

}
