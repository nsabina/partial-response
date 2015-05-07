package com.sabina.examples.springboot.partialresponse;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.sabina.examples.springboot.partialresponse.entity.rest.BooksResource;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		register(BooksResource.class);
	}

}
