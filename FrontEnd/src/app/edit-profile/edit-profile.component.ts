import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../add-to-cart/services/api.service';
import { AuthService } from '../add-to-cart/services/auth.service';
import { UserProfile } from '../models/user-profile';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  updateUserProfileResponse:any;
  response:any;
public userProfile:any;
private userId:number;
userProfileinput:UserProfile=new UserProfile;
  constructor(private api:ApiService,private authService:AuthService,private route:Router ) { }

  ngOnInit(): void {
  }

updateUserProfile(){
  this.userId=this.authService.getUserProfileTemp().userId;
  console.log(this.userProfileinput);
  this.authService.updateUserProfile(this.userId,this.userProfileinput)
  .subscribe((res)=>{
    this.updateUserProfileResponse=res;
    alert("Profile Updated");
this.route.navigate(['/addToCart']);
  })

}
}
