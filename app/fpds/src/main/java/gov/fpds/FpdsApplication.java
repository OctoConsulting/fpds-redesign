package gov.fpds;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.gson.GsonBuilder;

@SpringBootApplication
public class FpdsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FpdsApplication.class, args);
	}
	
	@Value("${elasticsearch.server-url}")
	private String elasticServerUrl;
	
	@Bean
	public JestClient getJestClientFactory() {
		 // Construct a new Jest client according to configuration via factory
		 System.out.println("Instantiating JestClient...");
		 JestClientFactory factory = new JestClientFactory();
		 factory.setHttpClientConfig(new HttpClientConfig
		                        .Builder(elasticServerUrl)
		                        .multiThreaded(true).gson(new GsonBuilder().setDateFormat("MM/dd/yyyy").create())
		                        .build());
		 JestClient client = factory.getObject();
		 System.out.println("JestClient Instantiated..." + client);
		 return client;
	}
}
