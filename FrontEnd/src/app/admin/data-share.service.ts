import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Product } from '../models/product';

@Injectable({
  providedIn: 'root'
})

export class DataShareService {
  currProduct: Product = new Product();
  sharedData: BehaviorSubject<Product> = new BehaviorSubject(this.currProduct);
  constructor() { }
  CurrProduct(currProduct: Product) {
    this.sharedData.next(currProduct);
  }

}
