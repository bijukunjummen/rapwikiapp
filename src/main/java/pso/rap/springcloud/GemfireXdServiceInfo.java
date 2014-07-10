package pso.rap.springcloud;

import org.springframework.cloud.service.ServiceInfo;
import org.springframework.cloud.service.common.RelationalServiceInfo;

@ServiceInfo.ServiceLabel("gemfirexd")
public class GemfireXdServiceInfo extends RelationalServiceInfo {

	private String uri = null;
	public GemfireXdServiceInfo(String id, String uriString) {
		super(id, uriString, "gemfirexd");
		this.uri = uriString;
	}

	@Override
	public String getJdbcUrl() {
		return this.uri;
	}
}
