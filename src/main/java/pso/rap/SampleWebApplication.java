package pso.rap;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import pso.rap.config.WebConfig;

@EnableAutoConfiguration
@ComponentScan
public class SampleWebApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {
		new SpringApplicationBuilder(SampleWebApplication.class).initializers(new SampleWebApplicationInitializer()).application()
				.run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		application.initializers(new SampleWebApplicationInitializer());
		application.sources(SampleWebApplication.class);
		return application;
	}
}
