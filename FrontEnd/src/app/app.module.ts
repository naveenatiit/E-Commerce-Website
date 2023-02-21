import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { LoginSuccessComponent } from './login-success/login-success.component';
import { AddToCartComponent } from './add-to-cart/add-to-cart.component';
import { CartComponent } from './add-to-cart/components/cart/cart.component';
import { HeaderComponent } from './add-to-cart/components/header/header.component';
import { ProductsComponent } from './add-to-cart/components/products/products.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProfileComponent } from './profile/profile.component';
import { EditProfileComponent } from './edit-profile/edit-profile.component';
import { OrderHistoryComponent } from './order-history/order-history.component';
import { AdminComponent } from './admin/admin.component';
import { AddProductsComponent } from './admin/add-products/add-products.component';
import { RemoveProductsComponent } from './admin/remove-products/remove-products.component';
import { UpdateProductsComponent } from './admin/update-products/update-products.component';
import { UpdateProfileComponent } from './admin/update-profile/update-profile.component';
import { OrderComponent } from './add-to-cart/components/order/order.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { UpdatePasswordComponent } from './update-password/update-password.component';
import { ApiService } from './add-to-cart/services/api.service';
import { AuthService } from './add-to-cart/services/auth.service';
import { CartService } from './add-to-cart/services/cart.service';
import { CardsComponent } from './cards/cards.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    LoginSuccessComponent,
    AddToCartComponent,
    CartComponent,
    HeaderComponent,
    ProductsComponent,
    ProfileComponent,
    EditProfileComponent,
    OrderHistoryComponent,
    AdminComponent,
    AddProductsComponent,
    RemoveProductsComponent,
    UpdateProductsComponent,
    UpdateProfileComponent,
    OrderComponent,
    AdminLoginComponent,
    UpdatePasswordComponent,
    CardsComponent,

  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    FlexLayoutModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
  ],
  providers: [
    ApiService,
    AuthService,
    CartService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
