package pso.rap.config;

import org.hibernate.dialect.MySQLDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import java.util.Properties;

@Configuration
@EnableJpaRepositories("pso.rap.repository")
@Profile("mysql")
public class MySqlRepositoryConfig extends AbstractJpaRepositoryConfig {

    protected String getPackagesToScan() {
        return "pso.rap.domain";
    }

    @Bean
    public JpaVendorAdapter getJpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setDatabasePlatform(MySQLDialect.class.getName());
        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(false);
        return jpaVendorAdapter;
    }

    protected Properties getAdditionalProperties() {
        Properties properties = new Properties();
        properties.setProperty(org.hibernate.cfg.Environment.HBM2DDL_AUTO, "update");
        return properties;
    }
}
