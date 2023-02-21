import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { AuthService } from '../add-to-cart/services/auth.service';
import { Login } from '../models/login';
import { CartService } from '../add-to-cart/services/cart.service';
import { UserProfile } from '../models/user-profile';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  email:string;
  loggedinUser:UserProfile;
  loginUserResponse: string;
  loginData = new Login();
  constructor(private authService: AuthService, private route: Router) { }

  ngOnInit(): void {

  }

  loginUser() {

      this.authService.loginUser(this.loginData)
        .subscribe(res => {
          this.loginUserResponse = res;

          if(this.loginUserResponse=="success"){
            this.authService.getUserProfile(this.loginData.email)
            .subscribe(res=>{
              this.loggedinUser=res
              localStorage.setItem("loggedinUser",JSON.stringify(this.loggedinUser));
              this.route.navigate(['/addToCart']);
            });
          }

          else{
            alert(this.loginUserResponse);
          }
        })
  }

  }






