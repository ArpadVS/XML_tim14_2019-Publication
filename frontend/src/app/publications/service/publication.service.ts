import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user.model';
import { BaseService } from 'src/app/shared/services/base.service';
import { Observable } from 'rxjs';
import { PaperLetter } from 'src/app/models/paper.letter.model';
import { SearchDTO } from 'src/app/models/searchDTO.model';

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
        console.log('dodat rad');
        this.router.navigateByUrl('publications/all');
      });
  }

  accept(id: string): void{
    this.http.put(`${this.baseUrl}${ENDPOINTS.SCIENTIFIC_PAPER}` + '/accept/' + id, this.httpOptions)
      .subscribe(result => {
        console.log(result);
        console.log('accepted');
      });
  }

  reject(id: string): void{
    this.http.put(`${this.baseUrl}${ENDPOINTS.SCIENTIFIC_PAPER}` + '/reject/' + id, this.httpOptions)
      .subscribe(result => {
        console.log(result);
        console.log('rejected');
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

  searchText(term: SearchDTO): Observable<any>{
    return this.http.post(`${this.baseUrl}${ENDPOINTS.SCIENTIFIC_PAPER}` + '/searchByText', term, this.httpOptions);
  }

  getForReview(): Observable<any>{
    return this.http.get(`${this.baseUrl}${ENDPOINTS.SCIENTIFIC_PAPER}` + '/forReview', this.httpOptions);
  }
}
