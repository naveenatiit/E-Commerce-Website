import { Component, OnInit } from '@angular/core';
import { AuthService } from '../add-to-cart/services/auth.service';
import { Login } from '../models/login';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.css']
})
export class UpdatePasswordComponent implements OnInit {
  getUpdatePasswordResponse:any;
passwordUpdateData: Login=new Login;
  constructor(private authService:AuthService) { }

  ngOnInit(): void {
  }
  updatePassword(){
this.authService.changePassword(this.passwordUpdateData.email,this.passwordUpdateData.password)
.subscribe((res:any)=>{
this.getUpdatePasswordResponse=res;
})
  }

}
