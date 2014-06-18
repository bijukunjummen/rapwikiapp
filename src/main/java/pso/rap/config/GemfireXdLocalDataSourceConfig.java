package pso.rap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Profile("gemfirexd-local")
@EnableConfigurationProperties({GemfireXdProperties.class})
public class GemfireXdLocalDataSourceConfig extends AbstractLocalDataSourceConfig {

    @Autowired
    private GemfireXdProperties gemfireXdProperties;

    @Bean
    public DataSource dataSource() {
        return createBasicDataSource(gemfireXdProperties.getUrl(), gemfireXdProperties.getDriver(), gemfireXdProperties.getUsername(), gemfireXdProperties.getPassword());
    }
}
