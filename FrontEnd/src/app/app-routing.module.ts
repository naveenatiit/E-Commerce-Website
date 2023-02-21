import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddToCartComponent } from './add-to-cart/add-to-cart.component';
import { CartComponent } from './add-to-cart/components/cart/cart.component';
import { OrderComponent } from './add-to-cart/components/order/order.component';
import { ProductsComponent } from './add-to-cart/components/products/products.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { AddProductsComponent } from './admin/add-products/add-products.component';
import { AdminComponent } from './admin/admin.component';
import { RemoveProductsComponent } from './admin/remove-products/remove-products.component';
import { UpdateProductsComponent } from './admin/update-products/update-products.component';
import { UpdateProfileComponent } from './admin/update-profile/update-profile.component';
import { AuthGuardService } from './auth-guard.service';
import { EditProfileComponent } from './edit-profile/edit-profile.component';

import { LoginComponent } from './login/login.component';
import { OrderHistoryComponent } from './order-history/order-history.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { UpdatePasswordComponent } from './update-password/update-password.component';

const routes: Routes = [
  { path: 'signup', component: RegisterComponent },
  { path: 'login', component: LoginComponent },

  { path: 'editProfile', component: EditProfileComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'orderHistory', component: OrderHistoryComponent },
  { path: 'adminLogin', component: AdminLoginComponent},
  {path:'order',component:OrderComponent},
  {path:'updatePassword',component:UpdatePasswordComponent},



  {
    path: 'admin', component: AdminComponent, canActivate: [AuthGuardService],
    children: [
      { path: 'addProducts', component: AddProductsComponent },
      { path: 'removeProducts', component: RemoveProductsComponent },
      { path: 'updateProduct', component: UpdateProductsComponent },
    ]
  },



  {
    path: 'addToCart', component: AddToCartComponent,
    children: [
      { path: 'products', component: ProductsComponent },
      { path: 'cart', component: CartComponent },
      { path: '', redirectTo: 'products', pathMatch: 'full' }
    ]
  },

  {path: '**', redirectTo: 'addToCart' }


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [
  AdminComponent,
  AddProductsComponent,
  RemoveProductsComponent,
  UpdateProductsComponent,
  UpdateProfileComponent
]
