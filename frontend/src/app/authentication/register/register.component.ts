import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { RegisterModel } from 'src/app/models/register.model';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {
  
  registerForm: FormGroup;
  hide = true;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      'username': ['', [
        Validators.required
      ]],
      'password': ['', [
        Validators.required,
        Validators.minLength(3),
        Validators.maxLength(30)
      ]],
      'name': ['', [
        Validators.required
      ]],
      'lastName': ['', [
        Validators.required
      ]],
       'email': ['', [
        Validators.required
      ]],
    });
    if (this.authService.isLogedIn()) {
      this.router.navigate(['certificates/all']);
    }
  }

  onRegisterSubmit() {
    let username:string = this.registerForm.get('username').value;
    let password:string = this.registerForm.get('password').value;
    let name:string = this.registerForm.get('name').value;
    let lastName:string = this.registerForm.get('lastName').value;
    let email:string = this.registerForm.get('email').value;

    let userData: RegisterModel = new RegisterModel(username, password, name, lastName, email);
    this.authService.register(userData);
  }

}
