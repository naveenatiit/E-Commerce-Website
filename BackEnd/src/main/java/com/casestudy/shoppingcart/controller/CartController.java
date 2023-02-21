package com.casestudy.shoppingcart.controller;

import java.util.ArrayList;
import java.util.List;

import javax.management.AttributeNotFoundException;

import com.casestudy.shoppingcart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.casestudy.shoppingcart.entities.Cart;
import com.casestudy.shoppingcart.entities.CartItems;
import com.casestudy.shoppingcart.entities.Product;
import com.casestudy.shoppingcart.repository.CartItemsRepository;
import com.casestudy.shoppingcart.repository.ProductRepository;
import com.casestudy.shoppingcart.service.CartService;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/")
@CrossOrigin("*")

public class CartController {

	@Autowired
	CartService cartService;


	@GetMapping("/cart/{userId}/add/{productId}")
	public String addToCart(@PathVariable int userId, @PathVariable int productId ) throws AttributeNotFoundException {
		cartService.addToCart(userId, productId);
		return "New product added";
	}

	@GetMapping("/cart/{userId}/getCart")
	public Cart getCart(@PathVariable int userId) {
		return cartService.getCartByUserId(userId);
	}

	@GetMapping("/cart/{userId}/getCartItem/{cartitemId}")
	public CartItems getCartItems(@PathVariable int userId, @PathVariable int cartitemId) {
		return cartService.getCartItemsByUserIdAndcartItemId(userId, cartitemId);
	}



	@PostMapping("/cart/{userId}/changeQuantity/{productId}")
	public void changeQuantity(@RequestParam int Quantity, @PathVariable int userId, @PathVariable int productId) {
		try {
			if(Quantity > 0) cartService.changeQuantity(Quantity, userId, productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/cart/emptyCart/{userId}")
	public Cart emptyCart(@PathVariable int userId) {
		return cartService.emptyCart(userId);
	}

	@GetMapping("/cart/changeQuantity/{changeBy}/{cartItemId}")
	public CartItems changeQuantityAdd(@PathVariable int cartItemId, @PathVariable int changeBy){
		return cartService.changeQuantityAdd(cartItemId, changeBy);
	}

	@DeleteMapping("/cart/deleteCartItem/{cartItemId}")
	public void deleteByCartitemId(@PathVariable int cartItemId) {
		cartService.deleteByCartitemId(cartItemId);
	}

	@DeleteMapping("/cart/{userId}/remove/{productId}")
	public String removeProduct(@PathVariable int userId, @PathVariable int productId) throws AttributeNotFoundException {
		return cartService.removeProduct(userId, productId);
	}

}
