package com.casestudy.shoppingcart.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.casestudy.shoppingcart.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>, CrudRepository<Product, Integer>{
	List<Product> findByCategory(String category);

	Product getProductByProductId(int productId);

	
	@Modifying
	@Transactional
	@Query(value="Delete from products_to_order WHERE cartitemid=(select cart_item_id from cart_items where product_id=:n) limit 1", nativeQuery = true)
	public int deleteproductsToOrder(@Param("n") int productId);
}
