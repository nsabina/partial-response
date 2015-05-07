package com.sabina.examples.springboot.partialresponse.entity.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sabina.examples.springboot.partialresponse.entity.Book;
import com.sabina.examples.springboot.partialresponse.service.BookService;

@Component
@Path("/books")
public class BooksResource {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	private BookService service = new BookService();
	
    @Path("{isbn}")
    public BookResource getBookResource(@PathParam("isbn") String isbn) {
        return new BookResource(isbn);
    }
    
    @GET
	@Produces(MediaType.APPLICATION_JSON)
    public List<JsonNode> getAll() {
    	List<JsonNode> nodesList = new ArrayList<JsonNode>();
    	for (Book book: service.getAll()) {
    		nodesList.add(mapper.valueToTree(book));
    	}
    	return nodesList;
   	
    }

}