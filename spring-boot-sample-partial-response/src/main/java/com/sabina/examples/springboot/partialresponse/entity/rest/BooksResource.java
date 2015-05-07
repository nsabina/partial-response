package com.sabina.examples.springboot.partialresponse.entity.rest;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.stereotype.Component;

@Component
@Path("/books")
public class BooksResource {
	
    @Path("{isbn}")
    public BookResource getBookResource(@PathParam("isbn") String isbn) {
        return new BookResource(isbn);
    }

}