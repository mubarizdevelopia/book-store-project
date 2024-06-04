package com.example.book_store.helper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.book_store.constant.ExceptionMessages;
import com.example.book_store.dao.entity.User;
import com.example.book_store.dao.repository.UserRepository;
import com.example.book_store.exceptions.NotFoundException;

@Component
public class UserHelper {
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(user.isEmpty()) {
			throw new NotFoundException(ExceptionMessages.USER_NOT_FOUND);
		}
		return user.get();
	}
}
