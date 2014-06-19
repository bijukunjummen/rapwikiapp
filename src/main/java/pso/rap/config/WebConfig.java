package pso.rap.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(includeFilters = @ComponentScan.Filter(Service.class), useDefaultFilters = false)
@ImportResource({"classpath:/META-INF/spring/pageaccess.xml", "classpath:/META-INF/spring/pageviewstats.xml"})
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/bands/partialsHome").setViewName("bands/partialsHome::content");
		registry.addViewController("/bands/partialsSummary").setViewName("bands/partialsSummary::content");
		registry.addViewController("/bands/partialsAbout").setViewName("bands/partialsAbout::content");
		registry.addViewController("/bands/partialsEdit").setViewName("bands/partialsEdit::content");
	}

}
