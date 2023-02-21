package com.casestudy.shoppingcart.controller;

import java.util.List;

import javax.management.AttributeNotFoundException;

import com.casestudy.shoppingcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.casestudy.shoppingcart.entities.CartItems;
import com.casestudy.shoppingcart.entities.Product;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

	@Autowired
    ProductService productService;

	@PostMapping("/addProduct")
	public String addProducts(@RequestBody Product product) {
		if (product.getName() == null || product.getCategory() == null || product.getPrice() <= 0.0)
			return "please enter correct products details";
		productService.addProducts(product);
		return "new Product added";
	}

	@PutMapping("/updateProduct/{productId}")
	public String updateProduct(@RequestBody Product product, @PathVariable(value = "productId") int productId) {
		productService.updateProduct(product, productId);
		return "Product updated";
	}

	@GetMapping("/getById/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable(value = "productId") int productId)
			throws AttributeNotFoundException {
		Product product = productService.getProductById(productId).orElseThrow();
		return ResponseEntity.ok().body(product);
	}

	@GetMapping("/{category}")
	public List<Product> getByCategory(@PathVariable(value = "category") String category) {
		return productService.getProductByCategory(category);
	}

	@GetMapping("/search/{searchString}")
	public List<Product> getProductBySearchString(@PathVariable String searchString) {
		return productService.getProductByStringSearch(searchString);
	}

	@GetMapping("/allProducts")
	public List<Product> getAll() {
		return productService.findAll();
	}

	@DeleteMapping("removeProduct/{productId}")
	public void removeProduct(@PathVariable int productId) {
		productService.removeProduct(productId);
	}
}
