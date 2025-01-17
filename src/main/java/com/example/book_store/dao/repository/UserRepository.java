package com.example.book_store.dao.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_store.dao.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findById(Long id);
}
