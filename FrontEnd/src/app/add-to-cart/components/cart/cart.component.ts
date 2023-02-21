import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cart } from 'src/app/models/cart';
import { CartItems } from 'src/app/models/cart-items';
import { AuthService } from '../../services/auth.service';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})

export class CartComponent implements OnInit {
  createOrderResponse: string;
  emptyCartResponse: Cart;

  removeCartItemresponse: any;
  ChangeQunatityResponse: any;
  getCartResponse: Cart;
  cartitems: CartItems[];
  products: any = [];
  cartItemsInCart: CartItems[];
  grandTotal: number = 0;
  thisCart: Cart;

  constructor(private cartService: CartService, private route: Router, private authService: AuthService) {
  }

  ngOnInit(): void {

    this.getUpdatedCart()
    this.cartService.getCart()
      .subscribe((res) => {
        this.getCartResponse = res;
        this.cartService.thisCart.next(this.getCartResponse);
        this.thisCart.products.forEach(element => {
          this.grandTotal = this.grandTotal + element.quantity * element.product.price;
        });

      })

  }

  getUpdatedCart() {
    this.cartService.thisCart
      .subscribe(res => {
        this.thisCart = res;
      })

  }

  removeItem(currCartitem: CartItems) {
    this.thisCart.products = this.thisCart.products.filter(item => item != currCartitem);
    this.cartService.removeCartItem(currCartitem)
      .subscribe((res) => {
        this.removeCartItemresponse = res;
        this.cartService.totalItem.next(this.thisCart.products.length);
        alert("Product Removed from Cart");
      });

  }

  emptyCart() {
    this.cartService.removeAllItems()
      .subscribe((res) => {
        this.cartService.thisCart.next(this.emptyCartResponse);
        this.cartService.totalItem.next(0);
        alert("your cart is empty now")
      })

  }

  orderProducts() {

    this.cartService.createOrder()
      .subscribe((res) => {
        this.createOrderResponse = res;
        alert("Order Placed Successfully");
        this.cartService.removeAllItems()
          .subscribe((res) => {
            this.cartService.thisCart.next(this.emptyCartResponse);
            this.cartService.totalItem.next(0);
          })
      })
  }


  updateQuantity(cartItem: CartItems, index: number, changeBy: number) {
    this.thisCart.products[index].quantity = this.thisCart.products[index].quantity + changeBy;
    this.cartService.changeQuantity(cartItem.cartItemId, changeBy)
      .subscribe((res) => {
        this.ChangeQunatityResponse = res;
      })
  }

}
