package pso.rap.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("gemfirexd-cloud")
@Configuration
public class GemfireXdCloudDataSourceConfig {

    @Value("${vcap.services.gemfirexd-service.credentials.gemfirexd.uri}")
    private String connectionString;

    private ConnectionInfo parseConnectionString(String s) {
        ConnectionInfo connectionInfo = new ConnectionInfo();
        String[] tokens = s.split(";");
        connectionInfo.uri = tokens[0];
        connectionInfo.userName = tokens[1].split("=")[1];
        connectionInfo.password = tokens[2].split("=")[1];
        return connectionInfo;
    }

    class ConnectionInfo {
        String userName;
        String password;
        String uri;
    }
}


