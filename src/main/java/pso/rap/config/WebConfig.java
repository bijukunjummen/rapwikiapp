package pso.rap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/rap/bands/partialsHome").setViewName("bands/partialsHome::content");
		registry.addViewController("/rap/bands/partialsSummary").setViewName("bands/partialsSummary::content");
		registry.addViewController("/rap/bands/partialsAbout").setViewName("bands/partialsAbout::content");
	}

}