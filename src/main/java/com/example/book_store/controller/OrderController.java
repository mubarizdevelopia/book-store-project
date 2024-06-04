package com.example.book_store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book_store.dao.entity.User;
import com.example.book_store.model.OrderDto;
import com.example.book_store.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/{userId}")
	public List<OrderDto> getAllOrders(@PathVariable("userId") Long userId) {
		return orderService.getAllOrdersByUserId(userId);
	}
	
	@PostMapping("/{userId}")
	public OrderDto createOrder(@PathVariable("userId")Long userId) {
		return orderService.createOrder(userId);
	}
	
	@PutMapping("/{userId}")
	public OrderDto assignOrderToCourier(@PathVariable("userId") Long userId) {
		return orderService.assignOrderToCourier(userId);
		
	}
}
