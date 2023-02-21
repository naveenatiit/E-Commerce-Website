package com.casestudy.shoppingcart.entities;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;


@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	private int userId;

	@JoinTable(name="CartProducts",
			joinColumns={@JoinColumn(name="Cart_Id")},
			inverseJoinColumns= {@JoinColumn(name="Cart_item_id")})
	@ElementCollection
	private List<CartItems> products;

	public Cart() {
		super();

	}

	public Cart(int cartId, int userId, List<CartItems> products) {
		super();
		this.cartId = cartId;
		this.userId = userId;
		this.products = products;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<CartItems> getProducts() {
		return products;
	}

	public void setProducts(List<CartItems> products) {
		this.products = products;
	}

}
