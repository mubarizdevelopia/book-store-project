package com.example.book_store.model;

import java.time.LocalDateTime;
import java.util.List;

import com.example.book_store.dao.entity.Book;

public class BasketDto {
	private LocalDateTime expireDate;
	private Long id;
	private List<Book> books;

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDateTime expireDate) {
		this.expireDate = expireDate;
	}
	
}
