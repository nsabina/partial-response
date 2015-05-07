package com.sabina.examples.springboot.partialresponse.service;

import org.springframework.stereotype.Component;

import com.sabina.examples.springboot.partialresponse.entity.Book;

@Component
public class Service {

	public Book getBook(String isbn) {
		Book book = new Book();
		book.setIsbn(isbn);
		return book;
	}

}
