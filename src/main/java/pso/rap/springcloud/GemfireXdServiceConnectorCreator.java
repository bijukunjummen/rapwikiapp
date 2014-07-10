package pso.rap.springcloud;

import org.springframework.cloud.service.relational.DataSourceCreator;


public class GemfireXdServiceConnectorCreator extends DataSourceCreator<GemfireXdServiceInfo> {
	
	private static final String[] DRIVERS = new String[]{"com.pivotal.gemfirexd.jdbc.ClientDriver"};
	private static final String VALIDATION_QUERY = "SELECT 1";
	
	public GemfireXdServiceConnectorCreator() {
		super("spring-cloud.gemfirexd.driver", DRIVERS, null);
	}
}