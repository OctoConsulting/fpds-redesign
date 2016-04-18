package gov.fpds;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FpdsApplication.class)
@WebIntegrationTest("server.port:0") 
public class FpdsApplicationTests  {
	
    @Value("${local.server.port}")  
    int port;
	
	RestTemplate template = new TestRestTemplate();

	@Test
	public void testApplicationUp() throws Exception {
		ResponseEntity<String> response = template.getForEntity("http://localhost:" + port, String.class);
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
