package pso.rap.springcloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.cloudfoundry.RelationalServiceInfoCreator;
import org.springframework.cloud.cloudfoundry.Tags;
import org.springframework.cloud.util.UriInfo;

import java.util.Map;

public class GemfireXdServiceInfoCreator extends RelationalServiceInfoCreator<GemfireXdServiceInfo> {

	private static final Logger logger = LoggerFactory.getLogger(GemfireXdServiceInfoCreator.class);

	public GemfireXdServiceInfoCreator() {
		super(new Tags("gemfirexd"), "jdbc:gemfirexd");
	}

	@Override
	public GemfireXdServiceInfo createServiceInfo(String id, String url) {
		return new GemfireXdServiceInfo(id, url);
	}

	@Override
	public GemfireXdServiceInfo createServiceInfo(Map<String,Object> serviceData) {
		@SuppressWarnings("unchecked")
		Map<String,Object> credentials = (Map<String, Object>) serviceData.get("credentials");

		String id = (String) serviceData.get("name");

		String uri = getStringFromCredentials(credentials, "uri", "url");

		return createServiceInfo(id, uri);
	}
}
