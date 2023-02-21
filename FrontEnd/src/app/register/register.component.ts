import { Component, OnInit } from '@angular/core';
import { UserRegisterService } from '../user-register.service';

import { NgForm } from '@angular/forms';
import { AuthService } from '../add-to-cart/services/auth.service';
import { Router } from '@angular/router';
import { User } from '../models/user';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})

export class RegisterComponent implements OnInit {
  allUsers: User[];
  registerUserData = new User();
  registerUserResponse: {};
  emailRegex: RegExp = new RegExp('[A-Za-z0-9._%-]+@[A-Za-z0-9._%-]+\\.[a-z]{2,3}')
  constructor(private authService: AuthService, private route: Router) { }

  ngOnInit(): void {
  }

  registerUser() {
    if(!this.registerUserData.email.includes('@') || this.registerUserData.email.length <8) {
      alert("invalid email id");
      return;
    }
    this.authService.getAllUser()
      .subscribe((res) => {
        this.allUsers = res;
        this.checkUserDatabase();
      })
  }

  checkUserDatabase():void{

    for (let index = 0; index < this.allUsers.length; index++){
      if (this.allUsers[index].email == this.registerUserData.email) {
      alert("Email already registered");
        return ;
      }
    }


    this.authService.registerUser(this.registerUserData)
    .subscribe(res => {
      this.registerUserResponse = res;
      console.warn(this.registerUserResponse);
      alert("Signup Successful")
      this.route.navigate(['/login']);
    })

  }


}
