package com.sabina.examples.springboot.partialresponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.sabina.examples.springboot.partialresponse.SampleJerseyApplication;
import com.sabina.examples.springboot.partialresponse.entity.Book;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SampleJerseyApplication.class)
@IntegrationTest("server.port=0")
@WebAppConfiguration
public class SampleJerseyApplicationTests {

	@Value("${local.server.port}")
	private int port;

	private RestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testingGetAll() {
		ResponseEntity<String> entity = this.restTemplate.getForEntity(
				"http://localhost:" + this.port + "/books", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void testingGetBook() {
		ResponseEntity<Book> entity = this.restTemplate.getForEntity(
				"http://localhost:" + this.port + "/books/isbn123", Book.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}

	@Test
	public void testingGetBookWithFields() {
		ResponseEntity<Book> entity = this.restTemplate.getForEntity(
				"http://localhost:" + this.port + "/books/isbn123?fields=isbn,title", Book.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
	}
	
	@Test
	public void testingGetBookWithObjectField() {
		ResponseEntity<Book> entity = this.restTemplate.getForEntity(
				"http://localhost:" + this.port + "/books/isbn123?fields=isbn,title,author.lastName", Book.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals("isbn123",entity.getBody().getIsbn());
		assertEquals("Smith",entity.getBody().getAuthor().getLastName());
		assertNull(entity.getBody().getAuthor().getFirstName());
	}


}
