import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/add-to-cart/services/api.service';
import { Product } from 'src/app/models/product';
import { DataShareService } from '../data-share.service';

@Component({
  selector: 'app-update-products',
  templateUrl: './update-products.component.html',
  styleUrls: ['./update-products.component.css']
})
export class UpdateProductsComponent implements OnInit {

  currProduct:Product;
  helper: String = 'not yet assigned';
  constructor(private api:ApiService, private dataSharedService: DataShareService, private route: Router) { }

  ngOnInit(): void {
    this.currProduct = this.dataSharedService.sharedData.getValue();
    this.helper = this.currProduct.subcategory.toString();
  }

  updateProduct(currProduct: Product, data:any){
    currProduct.subcategory = this.helper.split(',', 8);
    this.api.updateProduct(this.currProduct.productId, this.currProduct)
    .subscribe(res => console.log(res));
    this.route.navigate(['admin/removeProducts'])
  }

  }
