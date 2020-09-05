import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { BaseService } from 'src/app/shared/services/base.service';
import { Observable } from 'rxjs';
import { PaperLetter } from 'src/app/models/paper.letter.model';

const ENDPOINTS = {
  SCIENTIFIC_PAPER: '/scientificPaper',
  USER: '/user'
}


@Injectable({
  providedIn: 'root'
})
export class PublicationService extends BaseService{
  activeUser: User;
  httpOptions = {
    headers: new HttpHeaders({
      // 'Content-Type':  'application/xml',
      // 'Response-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    })
  };
  
  constructor(private http: HttpClient, private router: Router) {
    super();
    this.activeUser = JSON.parse( localStorage.getItem('user'));
  }

  add(paperLetter: PaperLetter): void{
    this.http.post(`${this.baseUrl}${ENDPOINTS.SCIENTIFIC_PAPER}`, paperLetter, this.httpOptions)
      .subscribe(result => {
        console.log(result);
        console.log('dodat rad')
        this.router.navigateByUrl('publications/all');
      });
  }

  getHTML(id: string){
    window.open(`${this.baseUrl}${ENDPOINTS.SCIENTIFIC_PAPER}` + '/html/' + id);
  }

  getPDF(id: string){
    window.open(`${this.baseUrl}${ENDPOINTS.SCIENTIFIC_PAPER}` + '/pdf/' + id);
  }

  getAll(): Observable<any>{
    return this.http.get(`${this.baseUrl}${ENDPOINTS.SCIENTIFIC_PAPER}`);
  }

  getMy(): Observable<any>{
    return this.http.get(`${this.baseUrl}${ENDPOINTS.SCIENTIFIC_PAPER}` + '/myPapers', this.httpOptions);
  }
}
