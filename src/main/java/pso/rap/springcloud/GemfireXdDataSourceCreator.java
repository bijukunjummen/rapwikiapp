package pso.rap.springcloud;

import org.springframework.cloud.service.relational.DataSourceCreator;

public class GemfireXdDataSourceCreator extends DataSourceCreator<GemfireXdServiceInfo> {
	private static final String[] DRIVER_CLASSES = {"com.pivotal.gemfirexd.jdbc.ClientDriver"};

	public GemfireXdDataSourceCreator() {
		super("spring-cloud.gemfirexd.driver", DRIVER_CLASSES, null);
	}
}
