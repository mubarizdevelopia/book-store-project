package com.example.book_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book_store.dao.entity.Book;
import com.example.book_store.model.BookDto;
import com.example.book_store.service.BookService;

@RestController
@RequestMapping("/v1/api/books")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@GetMapping
	public List<BookDto> getAllBooks(){
		return bookService.getAllBooks();
	}
	
	@GetMapping("/{id}")
	public BookDto getBookById(@PathVariable("id") Long id) {
		return bookService.findById(id);
	}
	
	@PostMapping
	public Book createBook(@RequestBody BookDto bookDto) {
		return bookService.createBook(bookDto);
	}

}
