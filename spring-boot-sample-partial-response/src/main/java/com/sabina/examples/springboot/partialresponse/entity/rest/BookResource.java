package com.sabina.examples.springboot.partialresponse.entity.rest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.sabina.examples.springboot.partialresponse.entity.Book;
import com.sabina.examples.springboot.partialresponse.service.BookService;

public class BookResource {
	private String isbn;
	private static ObjectMapper mapper = new ObjectMapper();

	private BookService service = new BookService();

	public BookResource(String isbn) {
		this.isbn = isbn;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public JsonNode getBook(@QueryParam("fields") String fieldsQueryParam) {
		Book book = service.getBook(isbn);
		JsonNode node = mapper.valueToTree(book);
		String fields[] = fieldsQueryParam != null ? fieldsQueryParam
				.split(",") : null;
		if (fields != null) {
			filterNode(node, new HashSet<String>(Arrays.asList(fields)), "");
		}
		return node;

	}

	private void filterNode(JsonNode node, Set<String> requiredFields,
			String parentPath) {

		Iterator<Entry<String, JsonNode>> fieldsIterator = node.fields();

		while (fieldsIterator.hasNext()) {
			Entry<String, JsonNode> mapEntry = fieldsIterator.next();
			String entryKey = mapEntry.getKey();
			JsonNode entryValue = mapEntry.getValue();
			String fieldPath;
			if (!parentPath.isEmpty()) {
				fieldPath = parentPath + "." + entryKey;
			} else {
				fieldPath = entryKey;
			}
			
			boolean contains = false;
			for (String field: requiredFields) {
				if (field.equals(fieldPath) || field.startsWith(fieldPath + ".")) {
					contains = true;
					break;
				}
			}
			
			if (!contains) {

				fieldsIterator.remove();

			} else {
				if (entryValue.isObject()) {
					filterNode(entryValue, requiredFields, fieldPath);
				} else if (entryValue.isArray()) {
					for (JsonNode arrayElementNode : (ArrayNode) entryValue) {
						filterNode(arrayElementNode, requiredFields, fieldPath);
					}
				}

			}

		}

	}
}
