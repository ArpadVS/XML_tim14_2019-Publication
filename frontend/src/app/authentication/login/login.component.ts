import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LoginModel } from 'src/app/models/login.model';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm: FormGroup;
  hide = true;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      'username': ['', [
        Validators.required
      ]],
      'password': ['', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(30)
      ]]
    });
    if (this.authService.isLogedIn()) {
      this.router.navigate(['certificates/all']);
    }
  }

  onLoginSubmit() {
    let username:string = this.loginForm.get('username').value;
    let password:string = this.loginForm.get('password').value;

    let userData: LoginModel = new LoginModel(username, password);
    this.authService.login(userData);
  }

}
