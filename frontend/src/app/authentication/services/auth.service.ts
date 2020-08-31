import { Injectable } from '@angular/core';
import { BaseService } from 'src/app/shared/services/base.service';
import { User } from 'src/app/models/user.model';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { LoginModel } from 'src/app/models/login.model';
import { map } from 'rxjs/operators';
import { async } from '@angular/core/testing';
import * as jwt_decode from 'jwt-decode';
import { RegisterModel } from 'src/app/models/register.model';

const ENDPOINTS = {
  LOGIN: '/user/login',
  REGISTER: '/user/register',
  LOGOUT: '/user/logout'
}


@Injectable({
  providedIn: 'root'
})
export class AuthService extends BaseService {

  activeUser: User;

  constructor(private http: HttpClient, private router: Router) {
    super();
    this.activeUser = JSON.parse( localStorage.getItem('user'));
  }

  login(userData: LoginModel) : void {
    this.http.post(`${this.baseUrl}${ENDPOINTS.LOGIN}`, userData)
      .pipe(
        map((res: Response) =>  new User().deserialize(res))
      ).subscribe((user: User) => {
        this.activeUser = user;
        localStorage.setItem('user', JSON.stringify(user));
        localStorage.setItem('token', this.activeUser.token);
        this.autoLogOut();
        this.router.navigateByUrl('publications/all');
      });
  }

  register(userData: RegisterModel): void{
    this.http.post(`${this.baseUrl}${ENDPOINTS.REGISTER}`, userData)
      .subscribe(result => {
        this.router.navigateByUrl('login');
      });
  }

  logOut(): void {
    this.http.post(`${this.baseUrl}${ENDPOINTS.LOGOUT}`, {})
      .subscribe(() => {
        this.activeUser = null;
        localStorage.removeItem('user');
        localStorage.removeItem('token');
        this.router.navigateByUrl('/');
      });
  }

  async autoLogOut() {
    const jwt = jwt_decode(this.activeUser.token);
    setTimeout(
      () => {
        this.activeUser = null;
        localStorage.removeItem('user');
        localStorage.removeItem('token');
        this.router.navigateByUrl('/');
      }, (jwt.exp * 1000) - (new Date()).getTime()
    );
  }

  isLogedIn(){
    const user: any = JSON.parse(localStorage.getItem('user'));
    if(user == null || this.activeUser == null){
      return false;
    }
    this.activeUser = user;
    const jwt = jwt_decode(this.activeUser.token);
    // console.log(jwt.role);
    const exp = (jwt.exp * 1000) - (new Date()).getTime();
    if (exp < 0){
      this.activeUser = null;
      localStorage.removeItem('user');
      localStorage.removeItem('token');
    }

    return this.activeUser != null;
  }

  isAdmin(){
    const user: any = JSON.parse(localStorage.getItem('user'));
    const actUser: User = user;
    const jwt = jwt_decode(actUser.token);
    return jwt.role === 'ROLE_ADMIN';
  }
}
