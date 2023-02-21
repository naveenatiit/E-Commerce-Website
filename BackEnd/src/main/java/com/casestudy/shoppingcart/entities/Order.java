package com.casestudy.shoppingcart.entities;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;

@Entity
@Table(name="orderedItem")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderId;
	
	private int userId;
	
	@JoinTable(name="ProductsToOrder",
			joinColumns={@JoinColumn(name="OrderId")},
			inverseJoinColumns= {@JoinColumn(name="Cartitemid")})
	@ElementCollection
	private List<CartItems> productsToOrder;
	
	private String orderStatus;

	public Order() {
		super();
	}

	public Order(int orderId, int userId, List<CartItems> productsToOrder, String orderStatus) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.productsToOrder = productsToOrder;
		this.orderStatus = orderStatus;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public List<CartItems> getProductsToOrder() {
		return productsToOrder;
	}

	public void setProductsToOrder(List<CartItems> productsToOrder) {
		this.productsToOrder = productsToOrder;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

}
