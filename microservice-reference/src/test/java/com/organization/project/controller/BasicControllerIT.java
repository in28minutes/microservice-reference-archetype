package com.organization.project.controller;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.organization.project.ProjectNameApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProjectNameApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BasicControllerIT {

	@LocalServerPort
	private int port;

	private URL base;
	private TestRestTemplate template;

	@Before
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:" + port);
		template = new TestRestTemplate();
	}

	@Test
	public void welcome() throws Exception {
		ResponseEntity<String> response = template.getForEntity(base.toString() + "/welcome", String.class);
		assertThat(response.getBody(), equalTo("Hello World"));
	}

	@Test
	public void welcomeWithObject() throws Exception {
		ResponseEntity<String> response = template.getForEntity(base.toString() + "/welcome-with-object", String.class);
		assertThat(response.getBody(), containsString("Hello World"));
	}

	@Test
	public void welcomeWithParameter() throws Exception {
		ResponseEntity<String> response = template.getForEntity(base.toString() + "/welcome-with-parameter/name/Buddy",
				String.class);
		assertThat(response.getBody(), containsString("Hello World, Buddy"));
	}
}