import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/add-to-cart/services/api.service';
import { CartService } from 'src/app/add-to-cart/services/cart.service';
import { Product } from 'src/app/models/product';
import { DataShareService } from '../data-share.service';


@Component({
  selector: 'app-remove-products',
  templateUrl: './remove-products.component.html',
  styleUrls: ['./remove-products.component.css']
})

export class RemoveProductsComponent implements OnInit {
    productList: Product[];
    removeProductResponse:any;
    change2 = 0;
    constructor(private api:ApiService,private cartService:CartService, private dataShareService: DataShareService , private route: Router) { }

    ngOnInit(): void {
      this.cartService.getProduct()
      .subscribe(res=>{
        this.productList=res;
        console.log(res);
      })
    }

    removeProduct(productId:number){
    this.api.removeProductService(productId)
    .subscribe(res=>{
      this.removeProductResponse=res;

      this.cartService.getProduct()
      .subscribe(res=>{
        this.productList=res;})
      alert('product removed')
      this.change2++;
    console.warn(this.removeProductResponse);
    })
    }

    updateProduct(product: Product) {
      this.dataShareService.CurrProduct(product);
      console.log(this.dataShareService.sharedData.getValue())
      this.route.navigate(['/admin/updateProduct'])
    }

}
