#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.controller;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;

import java.net.URI;
import java.net.URL;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ${package}.ProjectNameApplication;
import ${package}.bean.Todo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProjectNameApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoControllerIT {

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
	public void retrieveTodos() throws Exception {
		// When strict is set to false (recommended),
		// it forgives reordering data and extending results
		// (as long as all the expected elements are there),
		// making tests less brittle.
		String expected = "["
				+ "{id:1,user:Jack,desc:Learn Spring MVC,done:false}"
				+ ","
				+ "{id:2,user:Jack,desc:Learn Struts,done:false}"
				+ "]";

		ResponseEntity<String> response = template
				.getForEntity(
						base.toString()
								+ "/users/Jack/todos",
						String.class);
		JSONAssert.assertEquals(expected,
				response.getBody(), false);
	}

	@Test
	public void retrieveTodo() throws Exception {
		String expected = "{id:1,user:Jack,desc:Learn Spring MVC,done:false}";
		// _links:{"self":{"href":"http://localhost:56283/users/Jack/todos"}}
		ResponseEntity<String> response = template
				.getForEntity(
						base.toString()
								+ "/users/Jack/todos/1",
						String.class);
		JSONAssert.assertEquals(expected,
				response.getBody(), false);
	}

	@Test
	public void retrieveTodo_NotFound() throws Exception {
		// String expected = "{id:1,user:Jack,desc:Learn Spring
		// MVC,done:false}";
		// _links:{"self":{"href":"http://localhost:56283/users/Jack/todos"}}
		ResponseEntity<String> response = template
				.getForEntity(
						base.toString()
								+ "/users/Jack/todos/10",
						String.class);
		System.out.println(response.getBody());
		// JSONAssert.assertEquals(expected,
		// response.getBody(), false);
	}

	@Test
	public void addTodo() throws Exception {
		Todo todo = new Todo(-1, "Jill", "Learn Hibernate",
				new Date(), false);

		URI location = template.postForLocation(
				base.toString() + "/users/Jill/todos",
				todo);

		assertThat(location.getPath(),
				containsString("/users/Jill/todos/4"));
	}

}