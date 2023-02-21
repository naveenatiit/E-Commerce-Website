package com.casestudy.shoppingcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.casestudy.shoppingcart.entities.CartItems;
@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, Integer>, CrudRepository<CartItems, Integer> {

	public CartItems getBycartItemId(int cartItemId);

	public void deleteByCartItemId(int cartItemId);
	
//	@Modifying
//	@Transactional
//	@Query(value="DELETE FROM cart_items c WHERE c.cart_item_id= :p", nativeQuery = true)
//	public void deleteByCartItemId(@Param("p") int productId);
}
