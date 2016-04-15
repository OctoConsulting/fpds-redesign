package gov.fpds;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FpdsApplication.class)
@WebIntegrationTest
public class FpdsApplicationTests {
	
	RestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testApplicationUp() {
		
		/*restTemplate<String> entity = restTemplate.getForEntity(
				"https://localhost:" + this.port, String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());*/
	}

}
