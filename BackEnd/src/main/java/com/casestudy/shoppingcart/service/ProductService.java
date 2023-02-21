package com.casestudy.shoppingcart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.casestudy.shoppingcart.entities.CartItems;
import com.casestudy.shoppingcart.repository.CartItemsRepository;
import com.casestudy.shoppingcart.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.casestudy.shoppingcart.entities.Product;
import com.casestudy.shoppingcart.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired 
	ProductRepository productRepository ;

	@Autowired
	CartRepository cartRepository;
	@Autowired
	CartItemsRepository cartItemsRepository;
	
	
	public Product addProducts(Product product) {
		return productRepository.save(product);
	}

	public List<Product> getAll() {
		return productRepository.findAll();
	}

	public Optional<Product> getProductById(Integer productId) {
		// TODO Auto-generated method stub
		return productRepository.findById(productId);
	}

	public List<Product> getProductByCategory(String category) {
		
		List<Product> productsList = productRepository.findAll();
		List<Product> filteredList = new ArrayList<>();
		
		for(Product p : productsList) {
			if(p.getCategory().equalsIgnoreCase(category)) {
				filteredList.add(p);
			}
		}
		return filteredList;
//		return null;
	}

	
	public List<Product> getProductByStringSearch(String searchString) {

		List<Product> productsList = productRepository.findAll();
		List<Product> filteredList = new ArrayList<>();

		for(Product p : productsList) {
			if(p.getName().toLowerCase().contains(searchString.toLowerCase())||
					p.getCategory().toLowerCase().contains(searchString.toLowerCase()) ||
					p.getDetails().toLowerCase().contains(searchString.toLowerCase())) {
				filteredList.add(p);
			}
		}
		return filteredList;
	}

	
	public void updateProduct(Product product, int productId) {
		Product productToGetUpdate=productRepository.getProductByProductId(productId);
		productToGetUpdate.setCategory(product.getCategory());
		productToGetUpdate.setDetails(product.getDetails());
		productToGetUpdate.setName(product.getName());
		productToGetUpdate.setPrice(product.getPrice());
		productToGetUpdate.setImageUrl(product.getImageUrl());
		productToGetUpdate.setSubcategory(product.getSubcategory());
		productRepository.save(productToGetUpdate);
	}

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public void removeProduct(int productId) {
		int currCartItemid = 0;

		List<CartItems> allCartItems = cartItemsRepository.findAll();
		for (CartItems cartItems : allCartItems) {
			if (cartItems.getProduct().getProductId() == productId) {
				currCartItemid = cartItems.getCartItemId();

			}
		}

		productRepository.deleteproductsToOrder(productId);
		if (currCartItemid != 0) {

			cartRepository.deleteUsingCartItemId(currCartItemid);
			cartItemsRepository.deleteById(currCartItemid);
		}
		productRepository.deleteById(productId);
	}

}
