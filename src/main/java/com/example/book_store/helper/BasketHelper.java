package com.example.book_store.helper;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.book_store.constant.ExceptionMessages;
import com.example.book_store.dao.entity.Basket;
import com.example.book_store.dao.repository.BasketRepository;
import com.example.book_store.exceptions.NotFoundException;

@Component
public class BasketHelper {

	@Autowired
	private BasketRepository basketRepository;

	public Basket getValidBasket(Long userId) {
		Optional<Basket> basket = basketRepository.findTop1ByUserIdOrderByIdDesc(userId);
		if (basket.isEmpty() || basket.get().getExpireDate().isBefore(LocalDateTime.now())) {
			throw new NotFoundException(ExceptionMessages.BASKET_NOT_FOUND);
		}

		if (basket.get().getBooks().isEmpty())
			throw new NotFoundException("Kitab yoxdur");
		return basket.get();
	}
}
