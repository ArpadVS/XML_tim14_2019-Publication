import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { BaseService } from 'src/app/shared/services/base.service';

const ENDPOINTS = {
  SCIENTIFIC_PAPER: '/scientificPaper'
}


@Injectable({
  providedIn: 'root'
})
export class PublicationService extends BaseService{
  activeUser: User;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/xml',
      'Response-Type': 'application/json'
    })
  };
  
  constructor(private http: HttpClient, private router: Router) {
    super();
    this.activeUser = JSON.parse( localStorage.getItem('user'));
  }

  add(scientificPaper: string): void{
    this.http.post(`${this.baseUrl}${ENDPOINTS.SCIENTIFIC_PAPER}`, scientificPaper, this.httpOptions)
      .subscribe(result => {
        // console.log(result);
        console.log('dodat rad')
        this.router.navigateByUrl('publications/all');
      });
  }
}
