package com.ptsinfo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = PriceApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
@DirtiesContext
public class PriceApplicationTest {

	@Value("${local.server.port}")
	private int port;

	private RestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testPrice() throws Exception {
		ResponseEntity<String> entity = restTemplate.getForEntity(
				"http://localhost:" + this.port + "/price", String.class);
		assertThat(entity.getStatusCode(), is(HttpStatus.BAD_REQUEST));
	}

}