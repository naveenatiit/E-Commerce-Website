import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Login } from 'src/app/models/login';
import { User } from 'src/app/models/user';
import { UserProfile } from 'src/app/models/user-profile';

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  private registerUrl: string = "http://localhost:8080/signup";
  private loginUrl: string = "http://localhost:8080/login";
  private getProfileUrl: string = "http://localhost:8080/getProfileByEmail/";
  private getUserUrl: string = "http://localhost:8080/getUserByEmail/";
  private updateUserProfileUrl: string = "http://localhost:8080/updateProfile/";
  private updatePasswordUrl: string = "http://localhost:8080/forgotPassword/";
  private adminLoginUrl: string = "http://localhost:8080/admin/login/";
  private getAdminUrl: string = "http://localhost:8080/admin/getAdminByEmail";
  private adminLogoutUrl: string = "http://localhost:8080/admin/logout/";
  private userLogoutUrl: string = "http://localhost:8080/logout/";
  private deleteUserUrl: string = "http://localhost:8080/deleteAccount/";
  private getAllUserUrl: string = "http://localhost:8080/getAllUser";
  private getAllLoggedinUrl: string = "http://localhost:8080/allLoggedinUser";

  userProfileData = new UserProfile;
  public userProfileTemp: UserProfile;
  public userTemp: User;
  public adminTemp: User;
  logindata: Login;

  constructor(private http: HttpClient) {  }
   login(logindata: Login){
    return this.http.post<any>(this.loginUrl,logindata);

   }

  registerUser(user: any) {
    return this.http.post<any>(this.registerUrl, user);
  }
  loginUser(logindata: Login) {

    return this.http.post(this.loginUrl, logindata,{responseType:'text'});
  }
  getUserProfile(email: string) {
    return this.http.get<any>(this.getProfileUrl + email);
  }
  getUser(email: string) {
    return this.http.get<any>(this.getUserUrl + email);
  }

  updateUserProfile(userId: number, updateBody: UserProfile) {
    return this.http.put<any>(this.updateUserProfileUrl + userId, updateBody);
  }

  changePassword(email: string, password: string) {
    return this.http.put<any>(this.updatePasswordUrl + email, password);
  }

  adminLogin(email: string, password: string) {
    return this.http.post<any>(this.adminLoginUrl + email,password);
  }

  getAdmin(email: string) {
    return this.http.post<any>(this.getAdminUrl, email);
  }
  logoutAdmin(email: string) {
    return this.http.get<any>(this.adminLogoutUrl + email);
  }
  logoutUser(id: number) {
    return this.http.get<any>(this.userLogoutUrl + id);
  }
  deleteUser(id: number) {
    return this.http.delete<any>(this.deleteUserUrl + id);
  }
  getAllUser() {
    return this.http.get<any>(this.getAllUserUrl)
      .pipe(map((res: any) => { return res }));
  }
  getAllLoggedinUser() {
    return this.http.get<any>(this.getAllLoggedinUrl)
      .pipe(map((res: any) => { return res }));
  }

  setUserProfileData(userProfiledata: any) {
    this.userProfileTemp = userProfiledata;
    console.log(this.userProfileTemp);
  }

  setUserData(userData: any) {
    this.userTemp = userData;
    console.log(this.userTemp);
  }

  setAdminData(adminData: any) {
    this.adminTemp = adminData;
  }

  getUserTemp() {
    return this.userTemp;
  }

  getUserProfileTemp() {
    return this.userProfileTemp;
  }

  getAdminTemp() {
    return this.adminTemp;
  }

}
