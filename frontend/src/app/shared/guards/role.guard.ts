import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/authentication/services/auth.service';
import * as jwt_decode from 'jwt-decode';
import { User } from 'src/app/models/user.model';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {
  activeUser: User;
  constructor(
    public auth: AuthService,
    public router: Router

   ) { }

   canActivate(route: ActivatedRouteSnapshot): boolean {
    const user: any = JSON.parse(localStorage.getItem('user'));
    const expectedRoles: string = route.data.expectedRoles;
    // const token = localStorage.getItem('user');
    // const jwt = jwt_decode(token);
    this.activeUser = user;
    if (!user) {
      this.router.navigate(['login']);
      return false;
     }
    const token = this.activeUser.token;
    const jwt = jwt_decode(token);
    console.log(jwt.role);
    if (!token) {
     this.router.navigate(['login']);
     return false;
    }

    // const info = jwt.decodeToken(token);
    const roles: string[] = expectedRoles.split('|');

    if (roles.indexOf(jwt.role) === -1) {
     this.router.navigate(['publications/all']);
     return false;
    }
    return true;
}
}
