package com.example.book_store.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.book_store.dao.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

	Optional<Book> findById(Long id);
	
	List<Book> findBooksByIdIn(List<Long> id);
}
