import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/add-to-cart/services/api.service';
import { Product } from 'src/app/models/product';

@Component({
  selector: 'app-add-products',
  templateUrl: './add-products.component.html',
  styleUrls: ['./add-products.component.css']
})
export class AddProductsComponent implements OnInit {
  addProductResponse:any;
  subCategoryArray:string[];
    constructor(private api:ApiService) { }

    ngOnInit(): void {
    }
  addProduct(){

  }
  currProduct:Product=new Product();
  addProductdata(data:any){
  this.subCategoryArray=  data.subcategory.split(',', 8);
  this.currProduct.name=data.name;
  this.currProduct.category=data.category;
  this.currProduct.price=data.price;
  this.currProduct.details=data.details;
  this.currProduct.imageUrl=data.imageUrl;
  this.currProduct.subcategory=this.subCategoryArray;
  console.log(Product);
  this.api.addProduct(this.currProduct)
  .subscribe(res=>{
    this.addProductResponse=res;
  console.warn(this.addProductResponse);
  })
  }
}
