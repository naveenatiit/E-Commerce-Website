import { Component, OnInit } from '@angular/core';
import { Route, Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { Cart } from 'src/app/models/cart';
import { CartItems } from 'src/app/models/cart-items';
import { UserProfile } from 'src/app/models/user-profile';
import { User } from 'src/app/user';
import { AuthService } from '../../services/auth.service';
import { CartService } from '../../services/cart.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {
  keyword:string="";
  cartItemsInCart: CartItems[];
  getCartResponse: Cart;
  currUserCart: Cart;
  loginUserView: UserProfile;
  totalItem: number = 0;
  userLogoutResponse: any;
  email:string;
  showSearchBar: boolean;
  check : any;

  constructor(private cartService: CartService, private authservice: AuthService, private route: Router) {
  this.cartService.totalItem
  .subscribe(response=>{
  this.totalItem=response;
  console.log(this.totalItem);
  })

  this.route.events.subscribe((event) => {
    if (event instanceof NavigationEnd) {
      if (event.url.includes('/cart')) {
        this.showSearchBar = false;
      } else {
        this.showSearchBar = true;
      }
    }
  });
  }

   r= this.route.getCurrentNavigation.toString().includes("/cart")

  ngOnInit(): void {
    this.loginUserView=JSON.parse(localStorage.getItem("loggedinUser")||"");
    console.log(this.loginUserView);
    this.setCartProductsNo();
    console.log(this.r);
    this.check = localStorage.getItem('loggedinUser');
  }

  ngDoCheck(): void {
    this.r= this.route.getCurrentNavigation.toString().includes("/cart")
  }

  ngOnChange(){
    this.r= this.route.getCurrentNavigation.toString().includes("/cart")
    console.log("changes");
    console.log(this.r);
  }

  setCartProductsNo() {
    console.log(this.cartService.userProfile);
    this.cartService.getCart().
    subscribe(res=>{
    this.currUserCart=res;
  this.cartService.totalItem.next(this.currUserCart.products.length);
  })
  }

  searchProducts(){
    this.cartService.setKeyword(this.keyword);
    this.cartService.getFilteredProducts(this.keyword)
    .subscribe(res=>{this.cartService.productList.next(res)});
    console.log(this.cartService.productList);
    }


  updateProfile() {
    this.route.navigate(['/profile']);
  }

  logoutUser() {
    localStorage.removeItem("loggedinUser");
    this.authservice.logoutUser(this.loginUserView.userId)
      .subscribe((res) => {
        this.userLogoutResponse = res;
      })
  }
}
