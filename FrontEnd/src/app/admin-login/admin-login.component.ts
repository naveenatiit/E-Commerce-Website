import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../add-to-cart/services/auth.service';
import { Login } from '../models/login';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})

export class AdminLoginComponent implements OnInit {
  adminLoginData:Login=new Login;
  flag: boolean = true;
  adminLoginResponse:any;
  adminCreds: any;

  constructor(private authService:AuthService,private route:Router) { }

  ngOnInit(): void {
    }

  adminLogin(){
    this.authService.adminLogin(this.adminLoginData.email,this.adminLoginData.password)
    .subscribe((res)=>{
      if(res == "wrong") {
        console.log(res);
       alert("Wrong Credentials")
      }

      else if(res == "login working") {
        this.authService.getAdmin(this.adminLoginData.email)
        .subscribe((res)=>{
        this.adminCreds=res;
        localStorage.setItem("adminCreds",JSON.stringify(this.adminCreds));
        this.getAdmin();
        })
      }
    })
  }

  getAdmin(){
    this.authService.setAdminData(this.adminCreds);
    this.route.navigate(['/admin/removeProducts']);
  }
}
