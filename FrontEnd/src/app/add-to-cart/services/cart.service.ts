import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { AuthService } from './auth.service';
import { Product } from 'src/app/models/product';
import { CartItems } from 'src/app/models/cart-items';
import { Cart } from 'src/app/models/cart';
import { UserProfile } from 'src/app/models/user-profile';

@Injectable({
  providedIn: 'root'
})

export class CartService {
  totalItem = new BehaviorSubject<number>(0);
  userProfile: UserProfile;
  public cart = new Subject<Cart>();

  public cartItemList: Product[];
  public loggedInCart: Cart;
  public cartItemsTemp: CartItems[];

  keywordService = new BehaviorSubject("");
  public thisCart = new Subject<Cart>();
  public productList = new BehaviorSubject<Product[]>([]);


  constructor(private http: HttpClient, private authService: AuthService) {
  }

  getUserProfile(): UserProfile {
    this.userProfile = JSON.parse(localStorage.getItem("loggedinUser") || "");
    return this.userProfile;
  }

  getFilteredProducts(keyword: string) {
    return this.http.get<Product[]>("http://localhost:8080/products/search/" + keyword);
  }

  setKeyword(keyword: string) {
    this.keywordService.next(keyword);
  }

  changeQuantity(cartItemId: number, changeBy: number) {
    return this.http.get<any>("http://localhost:8080/cart/changeQuantity/" + changeBy + "/" + cartItemId);
  }

  getProduct() {
    return this.http.get<any>("http://localhost:8080/products/allProducts");
  }

  createOrder() {
    this.getUserProfile()
    return this.http.get("http://localhost:8080/order/" + this.userProfile.userId + "/createOrder", { responseType: 'text' })
  }
  addToCart(product: Product) {
    this.getUserProfile()
    return this.http.get("http://localhost:8080/cart/" + this.userProfile.userId + "/add/" + product.productId, { responseType: 'text' });
  }
  getCart() {
    this.getUserProfile()
    return this.http.get<Cart>("http://localhost:8080/cart/" + this.userProfile.userId + "/getCart");
  }


  removeCartItem(cartItem: CartItems) {
    return this.http.delete<any>("http://localhost:8080/cart/deleteCartItem/" + cartItem.cartItemId);
  }
  removeAllItems() {
    return this.http.get("http://localhost:8080/cart/emptyCart/" + this.getUserProfile().userId);
  }

}
