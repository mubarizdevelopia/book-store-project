package com.example.book_store.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.book_store.dao.entity.Basket;
import com.example.book_store.dao.entity.Book;
import com.example.book_store.dao.entity.Order;
import com.example.book_store.model.BasketDto;
import com.example.book_store.model.BookDto;
import com.example.book_store.model.OrderDto;

@Component
public class Mapper {
	
	
	public Book bookDtoToEntity(BookDto bookDto) {
		Book book = new Book();
		book.setAuthor(bookDto.getAuthor());
		book.setName(bookDto.getName());
		book.setPrice(bookDto.getPrice());
		return book;
	}
	
	public List<Book> bookDtoListToEntiyList(List<BookDto> bookDtos){
		List<Book> books  = new ArrayList<Book>();
		for (BookDto bookDto : bookDtos) {
			Book book = bookDtoToEntity(bookDto);
			books.add(book);
		}
		return books;
	}
	
	public BookDto bookEntityToDto(Book book) {
		BookDto bookDto = new BookDto();
		bookDto.setAuthor(book.getAuthor());
		bookDto.setName(book.getName());
		bookDto.setPrice(book.getPrice());
		return bookDto;
	}
	
	public List<BookDto> bookEntityListToDtoList(List<Book> books){
		List<BookDto> bookDtos = new ArrayList<>();
		for (Book book : books) {
			BookDto bookDto = bookEntityToDto(book);
			bookDtos.add(bookDto);
		}
		return bookDtos;
	}
	
	public BasketDto basketEntityToDto(Basket basket) {
		BasketDto basketDto = new BasketDto();
		basketDto.setExpireDate(basket.getExpireDate());
		basketDto.setId(basket.getId());
		basketDto.setBooks(basket.getBooks());
		return basketDto;
	}
	
	public Basket basketDtoToEntity(BasketDto basketDto) {
		Basket basket = new Basket();
		basket.setExpireDate(basketDto.getExpireDate());
		return basket;
	}
	
	
	public OrderDto orderEntityToDto(Order order) {
		OrderDto orderDto = new OrderDto();
		orderDto.setId(order.getId());
		orderDto.setBooks(order.getBooks());
		orderDto.setUserId(order.getUser().getId());
		orderDto.setStatus(order.getStatus());
		orderDto.setTotalAmount(order.getTotalPrice());
		return orderDto;
	}
	
	public List<OrderDto> orderListEntityToDtoList(List<Order> orders){
		List<OrderDto> orderDtos = new ArrayList<>();
		
		for (Order order : orders) {
			OrderDto orderDto = orderEntityToDto(order);
			orderDtos.add(orderDto);
		}
//		orders.stream().forEach(it->orderDtos.add(orderEntityToDto(it)));
		return orderDtos;
	}
}
