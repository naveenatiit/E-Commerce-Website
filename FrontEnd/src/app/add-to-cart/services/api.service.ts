import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { Product } from 'src/app/models/product';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http:HttpClient) { }

  getOrderHistory(userId:number){
    return this.http.get<any>("http://localhost:8080/order/"+userId+"/getOrders")
    .pipe(map((res:any)=>{return res}))
  }

  updateProduct(productId: number, product: Product) {
    return this.http.put<any>("http://localhost:8080/products/updateProduct/"+ productId, product )
    .pipe(map( res =>{return res;}))
  }

  removeProductService(productId:number){
    return this.http.delete<any>("http://localhost:8080/products/removeProduct/"+productId)
    .pipe(map((res:any)=>{return res}))
  }

  addProduct(product:Product){
    return this.http.post<any>("http://localhost:8080/products/addProduct/",product)
    .pipe(map((res:any)=>{return res}))
  }

}
