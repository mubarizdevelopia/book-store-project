package com.example.book_store.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_store.dao.entity.Basket;

public interface BasketRepository extends JpaRepository<Basket, Long> {
	
	Optional<Basket> findTop1ByUserIdOrderByIdDesc(Long userId);
	
	Basket findByUserId(Long userId);
}
