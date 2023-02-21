import { Component, OnInit } from '@angular/core';
import { AuthService } from '../add-to-cart/services/auth.service';
import { AdminLogin } from '../models/admin-login';
import { User } from '../user';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})

export class AdminComponent implements OnInit {
adminLoginData:AdminLogin;
adminLogoutResponse:any;

  constructor(private authService:AuthService) { }

  ngOnInit(): void {
    this.adminLoginData=this.authService.getAdminTemp();
  }

  logoutAdmin(email:string){
    localStorage.removeItem("adminCreds");
    this.authService.logoutAdmin(email)
    .subscribe((res)=>{
    this.adminLogoutResponse=res;
    })
  }


}
