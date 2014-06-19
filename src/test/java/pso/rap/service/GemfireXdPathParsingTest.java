package pso.rap.service;

import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

import java.util.List;

public class GemfireXdPathParsingTest {
	private String VCAPSTRING = "{\"p-rabbitmq-3.2.4\":[{\"name\":\"rabbit-rapwiki-instance\",\"label\":\"p-rabbitmq-3.2.4\",\"tags\":[\"rabbitmq\",\"messaging\",\"message-queue\",\"amqp\",\"stomp\"],\"plan\":\"standard\",\"credentials\":{\"uri\":\"amqp://u15e3ed6c951487e14514b0e15b4738f1dcb4e32f:pd5cf301f8636bb5acd9b69406359b3b8187730b3@172.16.0.54/fdf7681092b9c302d07821cd3391d96ebf5bfa1c\",\"vhost\":\"fdf7681092b9c302d07821cd3391d96ebf5bfa1c\",\"username\":\"u15e3ed6c951487e14514b0e15b4738f1dcb4e32f\",\"password\":\"pd5cf301f8636bb5acd9b69406359b3b8187730b3\",\"hostname\":\"172.16.0.54\",\"http_api_uri\":\"http://u15e3ed6c951487e14514b0e15b4738f1dcb4e32f:pd5cf301f8636bb5acd9b69406359b3b8187730b3@172.16.0.54:15672/api/\",\"protocols\":{\"amqp\":{\"uri\":\"amqp://u15e3ed6c951487e14514b0e15b4738f1dcb4e32f:pd5cf301f8636bb5acd9b69406359b3b8187730b3@172.16.0.54:5672/fdf7681092b9c302d07821cd3391d96ebf5bfa1c\",\"username\":\"u15e3ed6c951487e14514b0e15b4738f1dcb4e32f\",\"password\":\"pd5cf301f8636bb5acd9b69406359b3b8187730b3\",\"port\":5672,\"host\":\"172.16.0.54\",\"vhost\":\"fdf7681092b9c302d07821cd3391d96ebf5bfa1c\"},\"stomp\":{\"uri\":\"stomp://u15e3ed6c951487e14514b0e15b4738f1dcb4e32f:pd5cf301f8636bb5acd9b69406359b3b8187730b3@172.16.0.54:61613\",\"username\":\"u15e3ed6c951487e14514b0e15b4738f1dcb4e32f\",\"password\":\"pd5cf301f8636bb5acd9b69406359b3b8187730b3\",\"port\":61613,\"host\":\"172.16.0.54\",\"vhost\":\"fdf7681092b9c302d07821cd3391d96ebf5bfa1c\"},\"management\":{\"uri\":\"http://u15e3ed6c951487e14514b0e15b4738f1dcb4e32f:pd5cf301f8636bb5acd9b69406359b3b8187730b3@172.16.0.54:15672/api/\",\"username\":\"u15e3ed6c951487e14514b0e15b4738f1dcb4e32f\",\"password\":\"pd5cf301f8636bb5acd9b69406359b3b8187730b3\",\"port\":15672,\"host\":\"172.16.0.54\",\"path\":\"/api/\"}}}}],\"p-hd\":[{\"name\":\"p-hd-rapwiki-instance\",\"label\":\"p-hd\",\"tags\":[],\"plan\":\"Standard\",\"credentials\":{\"hadoop_username\":\"u6fa222994ca8430\",\"hdfs\":{\"configuration\":{\"fs.defaultFS\":\"hdfs://172.16.3.203:8020\"},\"directory\":\"/user/u6fa222994ca8430\"},\"gemfirexd\":{\"uri\":\"jdbc:gemfirexd://172.16.3.204:1527/;user=u6fa222994ca8430;password=77bc96ab-ef19-472a-46de-6a9bdbbdfb92\"}}}]}";

	@Test
	public void testVcapJsonParsing() {
		List<String> res = JsonPath.read(VCAPSTRING, "$..gemfirexd.uri");
		System.out.println(res);
	}
}
