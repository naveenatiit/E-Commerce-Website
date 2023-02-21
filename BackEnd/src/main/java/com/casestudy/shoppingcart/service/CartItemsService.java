package com.casestudy.shoppingcart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.shoppingcart.entities.CartItems;
import com.casestudy.shoppingcart.repository.CartItemsRepository;

@Service
public class CartItemsService {

	@Autowired
	CartItemsRepository cartItemsRepository;
	

	public CartItems addCartItemsToCart(CartItems cartItems) {
		return cartItemsRepository.save(cartItems);
	}

}
