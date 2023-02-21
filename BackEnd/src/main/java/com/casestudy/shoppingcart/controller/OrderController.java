package com.casestudy.shoppingcart.controller;

import java.util.ArrayList;
import java.util.List;

import com.casestudy.shoppingcart.repository.CartRepository;
import com.casestudy.shoppingcart.repository.OrderRepository;
import com.casestudy.shoppingcart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.shoppingcart.entities.Cart;
import com.casestudy.shoppingcart.entities.CartItems;
import com.casestudy.shoppingcart.entities.Order;
import com.casestudy.shoppingcart.service.OrderService;


@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class OrderController {

	@Autowired
	OrderService orderService;

	@GetMapping("/order/{userId}/createOrder")
	public String placeOrder(@PathVariable int userId) {
		try {
			return orderService.placeOrder(userId);
		} catch (Exception e) {
			e.printStackTrace();
			return "order failed";
		}
	}

	@GetMapping("/order/{userId}/getOrders")
	public List <Order> getOrderHistory(@PathVariable int userId) {
		return orderService.findAllByuserId(userId);
	}

}
