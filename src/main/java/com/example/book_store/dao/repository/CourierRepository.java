package com.example.book_store.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_store.dao.entity.Courier;
import com.example.book_store.model.CourierStatus;

public interface CourierRepository extends JpaRepository<Courier, Long> {
	Optional<Courier>findByStatus(CourierStatus courierStatus);
}
