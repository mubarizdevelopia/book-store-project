package com.example.book_store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.book_store.dao.entity.Courier;
import com.example.book_store.dao.repository.CourierRepository;

@RestController
@RequestMapping("/courier")
public class CourierController {

	@Autowired
	private CourierRepository courierRepository;
	
	@PostMapping
	public Courier courier(@RequestBody Courier courier) {
		return courierRepository.save(courier);
	}
}
