package com.casestudy.shoppingcart.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.casestudy.shoppingcart.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer>, CrudRepository<Cart, Integer> {

	public Cart findByUserId(int userId);
	@Modifying
	@Transactional
	@Query(value="Delete from cart_products WHERE cart_item_id= :n", nativeQuery = true)
	public int deleteUsingCartItemId(@Param("n") int cartItemId);

	Cart getCartByUserId(int userId);
}
