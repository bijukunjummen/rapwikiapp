package pso.rap.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pso.rap.repository.BandRepository;

@Configuration
@ComponentScan(basePackageClasses = {BandRepository.class})
public class RepositoryConfig {
}

