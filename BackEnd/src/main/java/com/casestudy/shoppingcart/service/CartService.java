package com.casestudy.shoppingcart.service;

import java.util.ArrayList;
import java.util.List;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.casestudy.shoppingcart.entities.Cart;
import com.casestudy.shoppingcart.entities.CartItems;
import com.casestudy.shoppingcart.entities.Product;
import com.casestudy.shoppingcart.entities.UserSignUpDetails;
import com.casestudy.shoppingcart.repository.CartItemsRepository;
import com.casestudy.shoppingcart.repository.CartRepository;
import com.casestudy.shoppingcart.repository.ProductRepository;
import com.casestudy.shoppingcart.repository.UserSignUpDetailsRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CartService {

	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	UserSignUpDetailsRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CartItemsRepository cartItemsRepository;
	
	@Autowired
	CartItemsService cartItemsService;
	CartService cartService;

	@Autowired
	UserSignUpDetailsService userSignUpDetailsService ;

	public Cart addToCart(int userId, int productId) throws AttributeNotFoundException {
		Product productToAddInCart = productService.getProductById(productId).orElseThrow();
		UserSignUpDetails curUser = userSignUpDetailsService.getUserById(userId).orElseThrow();
		Cart curUserCart = cartRepository.findByUserId(userId);
		CartItems newCartItem = new CartItems();
		
		if(curUserCart != null) {
			List<CartItems> cartItems = curUserCart.getProducts();
			boolean isProductAlreadyExist = false;
			
			for(CartItems cartItem : cartItems) {
				if(cartItem.getProduct().equals(productToAddInCart)) {
					isProductAlreadyExist = true;
					break;
				}
			}
			
			if(! isProductAlreadyExist) {
				//newCartItem.setCartItemId(cartItems.size() +1);
				newCartItem.setProduct(productToAddInCart);
				newCartItem.setQuantity(1);
				
				cartItemsService.addCartItemsToCart(newCartItem);
				cartItems.add(newCartItem);
			}
			
			curUserCart.setProducts(cartItems);
			return cartRepository.save(curUserCart);
		}
		
		Cart newCart = new Cart();
		List<CartItems> products = new ArrayList<>();
		
		//newCartItem.setCartItemId((int) cartItemsRepository.count());
		newCartItem.setProduct(productToAddInCart);
		newCartItem.setQuantity(1);
		
		cartItemsService.addCartItemsToCart(newCartItem);
		
		products.add(newCartItem);
		
		//newCart.setCartId((int) cartRepository.count());
		newCart.setUserId(userId);
		newCart.setProducts(products);

		return cartRepository.save(newCart);
	}

	
	public Cart getCartByUserId(int userId) {
		return cartRepository.findByUserId(userId);
	}

	
	public CartItems getCartItemsByUserIdAndcartItemId(int userId, int cartItemId) {
		Cart curUserCart = cartRepository.findByUserId(userId);
		List<CartItems> products = curUserCart.getProducts();
		
		for(CartItems cartItem : products) {
			if(cartItem.getCartItemId() == cartItemId) return cartItem;
		}
		
		return null;
	}
	
	public boolean removeProductFromCart(int userId, int productId) {
		Cart curUserCart = cartRepository.findByUserId(userId);
		
		List<CartItems> curUserProducts = curUserCart.getProducts();
		int index = 0;
		
		for(CartItems cartItem : curUserProducts) {
			if(cartItem.getProduct().getProductId() == productId) {
				curUserProducts.remove(index);
				cartItemsRepository.deleteById(cartItem.getCartItemId());
				curUserCart.setProducts(curUserProducts);
				cartRepository.save(curUserCart);
				return true;
			}
			index++;
		}
		
		return false;
	}

	public void changeQuantity(int Quantity, int userId, int productId) {
		Cart cart = getCartByUserId(userId);
		List<CartItems> products = cart.getProducts();
		for(CartItems cartItem : products) {
			if(cartItem.getProduct().getProductId() == productId) {
				cartItem.setQuantity(Quantity);
				CartItems newCartItem = cartItemsRepository.getBycartItemId(cartItem.getCartItemId());
				newCartItem.setQuantity(Quantity);
				cartItemsRepository.save(newCartItem);
				break;
			}
		}
		cart.setProducts(products);
		cartRepository.save(cart);
	}

	public Cart emptyCart(int userId) {
		Cart currCart=cartRepository.findByUserId(userId);
		Cart tempcurrCart=new Cart();
		tempcurrCart.setProducts(currCart.getProducts());
		tempcurrCart.setUserId(currCart.getUserId());

		for(CartItems itemsIncurrCart:tempcurrCart.getProducts()) {
			cartRepository.deleteUsingCartItemId(itemsIncurrCart.getCartItemId());
			cartItemsRepository.deleteById(itemsIncurrCart.getCartItemId());
		}
		currCart.setProducts(new ArrayList<CartItems>());
		cartRepository.save(currCart);
		return currCart;
	}

	public String removeProduct(int userId, int productId) throws AttributeNotFoundException {
		Product product = productRepository.findById(productId)
				.orElseThrow();
		if(cartService.removeProductFromCart(userId, productId)) {
			return "" + product.getName() + " removed from Your Cart";
		}
		return "No such productId exist in your cart removed from cart";
	}

	public CartItems changeQuantityAdd(int cartItemId, int changeBy){
		CartItems cartItem = cartItemsRepository.getBycartItemId(cartItemId);
		cartItem.setQuantity(cartItem.getQuantity()+changeBy);
		cartItemsRepository.save(cartItem);
		return cartItem;
	}

	public void deleteByCartitemId(int cartItemId) {
		cartRepository.deleteUsingCartItemId(cartItemId);
		cartItemsRepository.deleteById(cartItemId);
	}

	
}




