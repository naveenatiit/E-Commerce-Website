import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../add-to-cart/services/api.service';
import { CartService } from '../add-to-cart/services/cart.service';
import { DataShareService } from '../admin/data-share.service';
import { Product } from '../models/product';

@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.css']
})

export class CardsComponent implements OnInit {

  @Output() eventInfo1 = new EventEmitter();
  @Output() eventInfo2 = new EventEmitter();
  @Output() eventInfo3 = new EventEmitter();

  @Input() change = 0;
  @Input() change2=0;

  isAdminLoggedIn: any;
  isUserLoggedIn: any;
  productList: Product[];
  constructor(private api:ApiService, private cartService:CartService, private dataShareService: DataShareService , private route: Router) {
    this.cartService.getProduct()
      .subscribe(res => {
        this.productList = res;
      })
   }

  ngOnInit(): void {
    this.isAdminLoggedIn = localStorage.getItem('adminCreds');
    this.isUserLoggedIn = localStorage.getItem('loggedinUser');
  }

  ngOnChanges() {
    this.cartService.getProduct().
    subscribe(res => this.productList = res)
  }


  callAddToCart(item: Product) {
    this.eventInfo1.emit(item);
  }

  callRemoveProduct(productId: number) {
    this.eventInfo2.emit(productId);
  }

  callUpdateProduct(item: Product) {
    this.eventInfo3.emit(item);
  }

}
