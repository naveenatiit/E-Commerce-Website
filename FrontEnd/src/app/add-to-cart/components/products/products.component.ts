import { Component, Input, OnInit } from '@angular/core';
import { Cart } from 'src/app/models/cart';
import { Product } from 'src/app/models/product';
import { ApiService } from '../../services/api.service';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  keywordSecond: string = "";
  addToCartResponse: string;
  public productList: Product[];
  public filteredList: Product[];
  loggedInCart: Cart;
  change: number= 0;

  constructor(private api: ApiService, private cartService: CartService) {
    this.cartService.keywordService.subscribe(res => { this.keywordSecond = res });
    this.cartService.productList.subscribe(res => { this.productList = res });
    this.cartService.getProduct()
      .subscribe(res => {
        this.productList = res;
        this.filteredList = res;
      })
    console.log(this.productList);
  }

  ngOnInit(): void {
    this.cartService.getProduct()
      .subscribe(res => {
        this.productList = res;
        this.filteredList = res;
      })

  }
  //
  filterByCategory(category: string) {
    this.productList = [];
    console.log('hi')
    console.log(this.filteredList);
    this.filteredList.forEach(element => {
      if (element.category == category || category == "") {
        this.productList.push(element)
      }
    });
  }

  addToCart(item: Product) {
    let flag: boolean = true;
    this.cartService.getCart()
      .subscribe(response => {
        this.loggedInCart = response;

        if (response!=null){
          this.loggedInCart.products.forEach(Element => {
            if (item.productId == Element.product.productId) {
              this.cartService.changeQuantity(Element.cartItemId, 1)
              .subscribe(response=>{})
              console.log(Element.cartItemId);

              flag = false;
              alert("Product already exist in Cart");
            }
          }
        )
        }
        if (flag) {
          this.cartService.addToCart(item)
            .subscribe((res) => {
              this.addToCartResponse = res;
              console.log(this.loggedInCart.products.length);
              this.cartService.totalItem.next(this.loggedInCart.products.length + 1);
              this.change++;
              alert("Product added in Cart")
            })
        }
      });



  }


}

