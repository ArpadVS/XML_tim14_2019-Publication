import { Injectable } from '@angular/core';
import { User } from 'src/app/models/user.model';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BaseService } from 'src/app/shared/services/base.service';
import { Observable } from 'rxjs';

const ENDPOINTS = {
  REVIEW: '/review',
  REVISE: '/scientificPaper/revise/'
};

@Injectable({
  providedIn: 'root'
})

export class ReviewService  extends BaseService{

  activeUser: User;
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/xml',
      'Response-Type': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    })
  };

  constructor(private http: HttpClient, private router: Router) { 
    super();
    this.activeUser = JSON.parse( localStorage.getItem('user'));
  }

  add(review: string): void{
    this.http.post(`${this.baseUrl}${ENDPOINTS.REVIEW}`, review, this.httpOptions)
      .subscribe(result => {
        // console.log(result);
        console.log('dodat review');
        this.router.navigateByUrl('publications/all');
      });
  }

  addRevision(revision: string, id: string): void{
    this.http.put(`${this.baseUrl}${ENDPOINTS.REVISE}` + id, revision, this.httpOptions)
      .subscribe(result => {
        // console.log(result);
        console.log('dodat revision');
        this.router.navigateByUrl('publications/all');
      });
  }

  getHTML(id: string){
    window.open(`${this.baseUrl}${ENDPOINTS.REVIEW}` + '/html/' + id);
  }

  getPDF(id: string){
    window.open(`${this.baseUrl}${ENDPOINTS.REVIEW}` + '/pdf/' + id);
  }

  getAll(): Observable<any>{
    return this.http.get(`${this.baseUrl}${ENDPOINTS.REVIEW}`);
  }
}
