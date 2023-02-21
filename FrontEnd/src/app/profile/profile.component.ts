import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../add-to-cart/services/api.service';
import { AuthService } from '../add-to-cart/services/auth.service';
import { User } from '../models/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  orderHistoryresponse:any;
  deleteUserResponse:any;
  public userProfile:any;
  public user:User
  constructor(private api:ApiService, private authService:AuthService, private route:Router) { }

  ngOnInit(): void {

    this.userProfile=JSON.parse(localStorage.getItem("loggedinUser")||"");

  }

setUserOrderHistory(userId:number){
  this.api.getOrderHistory(this.userProfile.id)
  .subscribe((res)=>{
this.orderHistoryresponse=res;
console.log(this.orderHistoryresponse);

  })
  this.route.navigateByUrl("/orderHistory")
}
}
