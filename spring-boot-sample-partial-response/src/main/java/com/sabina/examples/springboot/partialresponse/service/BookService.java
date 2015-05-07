package com.sabina.examples.springboot.partialresponse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.sabina.examples.springboot.partialresponse.entity.Author;
import com.sabina.examples.springboot.partialresponse.entity.Book;

@Component
public class BookService {

	public Book getBook(String isbn) {
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle("My book");
		Author bookAuthor = new Author();
		bookAuthor.setFirstName("Bob");
		bookAuthor.setLastName("Smith");
		book.setAuthor(bookAuthor);
		
		return book;
	}

	public List<Book> getAll() {
		List<Book> booksList = new ArrayList<Book>();
		Book book1 = new Book();
		book1.setIsbn("12345");
		book1.setTitle("My book 1");
		Book book2 = new Book();
		book2.setIsbn("67890");
		book2.setTitle("My book 2");
		booksList.add(book1);
		booksList.add(book2);
		return booksList;

	}

}
