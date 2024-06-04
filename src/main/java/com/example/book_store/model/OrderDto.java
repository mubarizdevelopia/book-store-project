package com.example.book_store.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.book_store.dao.entity.Book;
import com.example.book_store.dao.entity.Courier;

@Component
public class OrderDto {
	private Long id;
	private List<Book> books;
	private Long userId;
	private Double totalAmount;
	private Status status;
	private Courier courier;
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Courier getCourier() {
		return courier;
	}
	public void setCourier(Courier courier) {
		this.courier = courier;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
