import { Component, OnInit } from '@angular/core';
import { ApiService } from '../add-to-cart/services/api.service';
import { AuthService } from '../add-to-cart/services/auth.service';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css']
})
export class OrderHistoryComponent implements OnInit {
  orderHistoryresponse:any;
  public orderList:any;
  public grandTotal: number=0;
  constructor(private api: ApiService, private authService:AuthService) { }

  ngOnInit(): void {
    this.api.getOrderHistory(this.authService.getUserTemp().id)
    .subscribe((res)=>{
  this.orderHistoryresponse=res;
  console.log(this.orderHistoryresponse);
    })
  }

}
