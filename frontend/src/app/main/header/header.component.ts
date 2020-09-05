import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/authentication/services/auth.service';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(
    private authService: AuthService,
  ) { }

  ngOnInit(): void {
  }

  get isUserLoggedIn() {
    return !!this.authService.isLogedIn();
  }

  get canReview(){
    return !!this.authService.canReview();
  }
  get isAdmin(){
    return this.authService.isAdmin();
  }

  logout(){
    this.authService.logOut();
  }

  get username(){
    return this.authService.getUsername();
  }

}
