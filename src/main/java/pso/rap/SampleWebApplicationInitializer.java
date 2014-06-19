package pso.rap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.context.embedded.AnnotationConfigEmbeddedWebApplicationContext;
import org.springframework.cloud.Cloud;
import org.springframework.cloud.CloudException;
import org.springframework.cloud.CloudFactory;
import org.springframework.cloud.service.ServiceInfo;
import org.springframework.cloud.service.common.AmqpServiceInfo;
import org.springframework.cloud.service.common.MysqlServiceInfo;
import org.springframework.cloud.service.common.RedisServiceInfo;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

import java.util.*;

public class SampleWebApplicationInitializer implements ApplicationContextInitializer<AnnotationConfigEmbeddedWebApplicationContext> {

    public static final String IN_MEMORY_PROFILE = "in-memory";
    private static final Log logger = LogFactory.getLog(SampleWebApplicationInitializer.class);
    private static final Map<Class<? extends ServiceInfo>, String> serviceTypeToProfileName =
            new HashMap<Class<? extends ServiceInfo>, String>();
    private static final List<String> validLocalProfiles = Arrays.asList("mysql", "gemfirexd", "rabbit", "redis");
    static {
        serviceTypeToProfileName.put(MysqlServiceInfo.class, "mysql");
//        serviceTypeToProfileName.put(GemfireXdServiceInfo.class, "gemfirexd");
        serviceTypeToProfileName.put(AmqpServiceInfo.class, "rabbit");
        serviceTypeToProfileName.put(RedisServiceInfo.class, "redis");
    }

    @Override
    public void initialize(AnnotationConfigEmbeddedWebApplicationContext applicationContext) {
        Cloud cloud = getCloud();

        ConfigurableEnvironment appEnvironment = applicationContext.getEnvironment();

        List<String[]> persistenceProfiles = getCloudProfile(cloud);
        if (persistenceProfiles == null) {
            persistenceProfiles = getActiveProfile(appEnvironment);
        }
        if (persistenceProfiles == null) {
            persistenceProfiles = new ArrayList<String[]>();
            persistenceProfiles.add(new String[] { IN_MEMORY_PROFILE });
        }
        for (String[] profile : persistenceProfiles) {
            for (String persistenceProfile : profile) {
                appEnvironment.addActiveProfile(persistenceProfile);
            }
        }

		for (String profile: customProfiles()) {
			appEnvironment.addActiveProfile(profile);
		}

        logger.info("Active profiles: " + StringUtils.arrayToCommaDelimitedString(appEnvironment.getActiveProfiles()));
    }

    public List<String[]> getCloudProfile(Cloud cloud) {
        if (cloud == null) {
            return null;
        }

        List<String> profiles = new ArrayList<String>();

        List<ServiceInfo> serviceInfos = cloud.getServiceInfos();

        logger.info("Found serviceInfos: " + StringUtils.collectionToCommaDelimitedString(serviceInfos));

        for (ServiceInfo serviceInfo : serviceInfos) {
            if (serviceTypeToProfileName.containsKey(serviceInfo.getClass())) {
                profiles.add(serviceTypeToProfileName.get(serviceInfo.getClass()));
            }
        }

//        if (profiles.size() > 1) {
//            throw new IllegalStateException(
//                    "Only one service of the following types may be bound to this application: " +
//                            serviceTypeToProfileName.values().toString() + ". " +
//                            "These services are bound to the application: [" +
//                            StringUtils.collectionToCommaDelimitedString(profiles) + "]");
//        }

        if (profiles.size() > 0) {
            return createProfileNames(profiles, "cloud");
        }

        return null;
    }

	private List<String> customProfiles() {
		List<String> customProfiles = new ArrayList<String>();
		String vcapServices =  System.getenv("VCAP_SERVICES");
		if (vcapServices!=null && vcapServices.contains("gemfirexd")) {
			customProfiles.add("gemfirexd-cloud");
		}
		return customProfiles;
	}

    private Cloud getCloud() {
        try {
            CloudFactory cloudFactory = new CloudFactory();
            return cloudFactory.getCloud();
        } catch (CloudException ce) {
            return null;
        }
    }

    private List<String[]> getActiveProfile(ConfigurableEnvironment appEnvironment) {
        List<String> serviceProfiles = new ArrayList<String>();

        for (String profile : appEnvironment.getActiveProfiles()) {
            if (validLocalProfiles.contains(profile)) {
                serviceProfiles.add(profile);
            }
        }

//        if (serviceProfiles.size() > 1) {
//            throw new IllegalStateException("Only one active Spring profile may be set among the following: " +
//                    validLocalProfiles.toString() + ". " +
//                    "These profiles are active: [" +
//                    StringUtils.collectionToCommaDelimitedString(serviceProfiles) + "]");
//        }

        if (serviceProfiles.size() > 0) {
            logger.info("Profile found: '" + serviceProfiles.get(0) + "'");
            return createProfileNames(serviceProfiles, "local");
        }
        logger.warn("No profile was set.");
        return null;
    }

    private List<String[]> createProfileNames(List<String> baseNames, String suffix) {
        List<String[]> profileNames = new ArrayList<String[]>();
        for (String baseName : baseNames) {
            String[] profiles = {baseName, baseName + "-" + suffix};
            logger.info("Setting profile names: " + StringUtils.arrayToCommaDelimitedString(profiles));
            profileNames.add(profiles);

        }
        return profileNames;
    }
}
