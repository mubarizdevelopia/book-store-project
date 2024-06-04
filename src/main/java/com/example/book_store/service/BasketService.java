package com.example.book_store.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book_store.constant.ExceptionMessages;
import com.example.book_store.dao.entity.Basket;
import com.example.book_store.dao.entity.Book;
import com.example.book_store.dao.entity.User;
import com.example.book_store.dao.repository.BasketRepository;
import com.example.book_store.dao.repository.BookRepository;
import com.example.book_store.dao.repository.UserRepository;
import com.example.book_store.exceptions.NotFoundException;
import com.example.book_store.helper.UserHelper;
import com.example.book_store.mapper.Mapper;
import com.example.book_store.model.BasketDto;

import net.bytebuddy.asm.Advice.Return;

@Service
public class BasketService {

	@Autowired
	private BasketRepository basketRepository;
	
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserHelper userHelper;

	@Autowired
	private Mapper mapper;

	public BasketDto findByUserId(Long userId) {
		User user = userHelper.getUserById(userId);
		Optional<Basket> basket = basketRepository.findTop1ByUserIdOrderByIdDesc(userId);
		if (basket.isEmpty() || basket.get().getExpireDate().isBefore(LocalDateTime.now())) {
			Basket createdBasket = new Basket();
			setExpireDate(createdBasket);
			createdBasket.setUser(user);
			createdBasket = basketRepository.save(createdBasket);
			return mapper.basketEntityToDto(createdBasket);
		}
		return mapper.basketEntityToDto(basket.get());
	}

	
	public Basket addBookToBasket(Long userId, List<Long> bookIds) {
		User user = userHelper.getUserById(userId);
		List<Book> selectedBooks = bookRepository.findBooksByIdIn(bookIds);
		Optional<Basket> basket = basketRepository.findTop1ByUserIdOrderByIdDesc(userId);
		basket.get().setBooks(selectedBooks);
		basketRepository.save(basket.get());
		return basket.get();
	}
	
	private void setExpireDate(Basket baseket) {
		baseket.setExpireDate(LocalDateTime.now().plusDays(3));
	}
}
