package com.example.book_store.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book_store.dao.entity.Basket;
import com.example.book_store.dao.entity.Book;
import com.example.book_store.dao.entity.Courier;
import com.example.book_store.dao.entity.Order;
import com.example.book_store.dao.entity.User;
import com.example.book_store.dao.repository.BasketRepository;
import com.example.book_store.dao.repository.CourierRepository;
import com.example.book_store.dao.repository.OrderRepository;
import com.example.book_store.exceptions.NotFoundException;
import com.example.book_store.helper.BasketHelper;
import com.example.book_store.helper.UserHelper;
import com.example.book_store.mapper.Mapper;
import com.example.book_store.model.CourierStatus;
import com.example.book_store.model.OrderDto;
import com.example.book_store.model.Status;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private BasketRepository basketRepository;

	@Autowired
	private CourierRepository courierRepository;

	@Autowired
	private Mapper mapper;

	@Autowired
	private UserHelper userHelper;

	@Autowired
	private BasketHelper basketHelper;

	public List<OrderDto> getAllOrdersByUserId(Long userId) {
		List<Order> orders = orderRepository.findByUserId(userId);
		if (orders.isEmpty())
			return new ArrayList<OrderDto>();
		return mapper.orderListEntityToDtoList(orders);
	}

	public OrderDto createOrder(Long userId) {
		User user = userHelper.getUserById(userId);
		Basket basket = basketHelper.getValidBasket(userId);
		Double totalPrice = calculateTotalPrice(basket);
		List<Book> selectedBooks = new ArrayList<>();
		selectedBooks.addAll(basket.getBooks());
		Order order = new Order();
		order.setUser(user);
		order.setBooks(selectedBooks);
		order.setStatus(Status.SOLD);
		order.setTotalPrice(totalPrice);
		orderRepository.save(order);
		return mapper.orderEntityToDto(order);
	}

	public OrderDto assignOrderToCourier(Long userId) {
		User user = userHelper.getUserById(userId);
		Optional<Order> order = orderRepository.findByUserIdAndStatus(userId, Status.SOLD);
		if (order.isEmpty())
			throw new NotFoundException("Order not found");
		Optional<Courier> courier = courierRepository.findByStatus(CourierStatus.FREE);
		if(courier.isEmpty()) {
			throw new NotFoundException("Free Courier not found");
		}
		order.get().setCourier(courier.get());
		order.get().setStatus(Status.DELIVERY);
		orderRepository.save(order.get());
		return mapper.orderEntityToDto(order.get());
	}

	private Double calculateTotalPrice(Basket basket) {
		List<Book> selectedBooks = basket.getBooks();
		Double totalPrice = Double.valueOf(0);
		for (Book book : selectedBooks) {
			totalPrice += book.getPrice();
		}

		return totalPrice;
	}

}
