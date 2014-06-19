package pso.rap.springcloud;

import org.springframework.cloud.service.ServiceInfo;
import org.springframework.cloud.service.common.RelationalServiceInfo;

@ServiceInfo.ServiceLabel("gemfirexd")
public class GemfireXdServiceInfo extends RelationalServiceInfo {
	public GemfireXdServiceInfo(String id, String uriString) {
		super(id, uriString, "gemfirexd");
	}
}
