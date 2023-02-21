package com.casestudy.shoppingcart.service;

import java.util.ArrayList;
import java.util.List;

import com.casestudy.shoppingcart.entities.Cart;
import com.casestudy.shoppingcart.entities.CartItems;
import com.casestudy.shoppingcart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.shoppingcart.entities.Order;
import com.casestudy.shoppingcart.repository.OrderRepository;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepository;
	@Autowired
	CartService cartService;
	@Autowired
	CartRepository cartRepository;
	

	public List<Order> findAllByuserId(int userId) {
		List <Order> allOrders=orderRepository.findAll();
		List<Order> userOrders=new ArrayList<Order>();
		for(Order order: allOrders) {
			if(order.getUserId()==userId) {
				userOrders.add(order);
			}
		}
		return userOrders;
	}

	public String placeOrder(int userId) {
		Cart curUserCart = cartService.getCartByUserId(userId);
		Order toPlacedOrder = new Order();
		toPlacedOrder.setUserId(userId);
		toPlacedOrder.setProductsToOrder(new ArrayList<CartItems>(curUserCart.getProducts()));
		toPlacedOrder.setOrderStatus("Placed Successfully");
		orderRepository.save(toPlacedOrder);

		Cart currCart=cartRepository.findByUserId(userId);
		Cart tempCurrCart=new Cart();
		tempCurrCart.setProducts(currCart.getProducts());
		tempCurrCart.setUserId(currCart.getUserId());

		for(CartItems itemsInCurrCart:tempCurrCart.getProducts()) {
			cartRepository.deleteUsingCartItemId(itemsInCurrCart.getCartItemId());
		}
		currCart.setProducts(new ArrayList<CartItems>());
		cartRepository.save(currCart);
		return "Order Successful";
	}

}
