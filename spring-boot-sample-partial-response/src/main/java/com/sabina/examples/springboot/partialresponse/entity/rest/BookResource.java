package com.sabina.examples.springboot.partialresponse.entity.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabina.examples.springboot.partialresponse.entity.Book;
import com.sabina.examples.springboot.partialresponse.service.Service;

public class BookResource {
	private String isbn;
	private static ObjectMapper mapper = new ObjectMapper();
	
	private Service service = new Service();

	public BookResource(String isbn) {
		this.isbn = isbn;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JsonNode getBook(@QueryParam("fields") String fieldsQueryParam) {
		Book book = service.getBook(isbn);
		JsonNode node = mapper.valueToTree(book);
        String fields[] = fieldsQueryParam != null ? fieldsQueryParam.split(",") : null;
        if(fields != null) {
             filterNode(node, arrayToSet(fields));
        }
		return node;

	}
	
    private void filterNode(JsonNode node, Set<String> fields) {
       
    }

    private <T> Set<T> arrayToSet(T[] values) {
       Set<T> result = new HashSet<>();
       return result;
      
    }
}
