package pso.rap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/bands/partialsHome").setViewName("bands/partialsHome::content");
		registry.addViewController("/bands/partialsSummary").setViewName("bands/partialsSummary::content");
		registry.addViewController("/bands/partialsAbout").setViewName("bands/partialsAbout::content");
	}

}