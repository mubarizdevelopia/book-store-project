package com.example.book_store.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_store.dao.entity.Order;
import com.example.book_store.model.CourierStatus;
import com.example.book_store.model.Status;

public interface OrderRepository extends JpaRepository<Order, Long> {
	List<Order> findByUserId(Long userId);

	Optional<Order> findByUserIdAndId(Long userId, Long id);
	
	Optional<Order> findByUserIdAndStatus(Long userId,Status status);
}
