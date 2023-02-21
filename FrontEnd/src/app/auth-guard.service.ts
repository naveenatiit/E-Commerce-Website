import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {

  constructor(private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const isLoggedIn = localStorage.getItem('adminCreds');
    if (!isLoggedIn) {
      alert('You are not Admin!!!')
      this.router.navigate(['/login']);
      return false;
    }

    return true;
  }
}






