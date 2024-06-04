package com.example.book_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book_store.dao.entity.Basket;
import com.example.book_store.model.BasketDto;
import com.example.book_store.model.BookDto;
import com.example.book_store.service.BasketService;

@RestController
@RequestMapping("/v1/api/baskets")
public class BasketController {

	@Autowired
	private BasketService basketService;
	
	@GetMapping("/{userId}")
	public BasketDto findByUserId(@PathVariable("userId") Long userId) {
		return basketService.findByUserId(userId);
	}
	
	@PutMapping("/{userId}")
	public Basket addBookToBasket(@RequestBody List<Long> bookIds,
			@PathVariable("userId") Long userId) {
		return basketService.addBookToBasket(userId, bookIds);
	}
}
