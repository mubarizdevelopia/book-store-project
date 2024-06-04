package com.example.book_store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book_store.constant.ExceptionMessages;
import com.example.book_store.dao.entity.Book;
import com.example.book_store.dao.repository.BookRepository;
import com.example.book_store.exceptions.NotFoundException;
import com.example.book_store.mapper.Mapper;
import com.example.book_store.model.BookDto;

@Service
public class BookService {

	@Autowired
	private Mapper mapper;

	@Autowired
	private BookRepository bookRepository;

	public List<BookDto> getAllBooks() {
		List<Book> books = bookRepository.findAll();
		List<BookDto> bookDtos = mapper.bookEntityListToDtoList(books);
		return bookDtos;
	}

	public BookDto findById(Long id) {
		Optional<Book> book = bookRepository.findById(id);
		if (book.isEmpty()) {
			throw new NotFoundException(ExceptionMessages.BOOK_NOT_FOUND);
		}
		BookDto bookDto = mapper.bookEntityToDto(book.get());
		return bookDto;
	}
	
	public Book createBook(BookDto bookDto) {
		Book book = mapper.bookDtoToEntity(bookDto);
		bookRepository.save(book);
		return book;
	}
}
