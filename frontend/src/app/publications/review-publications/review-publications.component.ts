import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { Publication } from 'src/app/models/publication.model';
import { PublicationService } from '../service/publication.service';

@Component({
  selector: 'app-review-publications',
  templateUrl: './review-publications.component.html',
  styleUrls: ['./review-publications.component.scss']
})
export class ReviewPublicationsComponent implements OnInit {

  publications: Publication[];

  dataSource: MatTableDataSource<Publication>;

  constructor(
    private publicationService: PublicationService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this._getData();
  }

  _getData(){
    console.log('getting data');
    this.publicationService.getForReview().subscribe((res: Publication[]) => {
      if (res != null) {
        this.publications = res;
        console.log(res);
        this.initializeDataSource();
      }
    });
  }

  initializeDataSource() {
    this.dataSource  = new MatTableDataSource<Publication>();
    this.dataSource.data = this.publications || [];
  }

  getDisplayedColumns(){
    return ['Index', 'Author', 'Title', 'HTML', 'PDF', 'Accept', 'Reject', 'Revision Needed'];
  }

  redirectToAddNewPage() {
    this.router.navigate(['publications/add']);
  }

  delete(publicationId){
    console.log('deleting');
  }

  getHTML(id: string){
    this.publicationService.getHTML(id);
  }

  getPDF(id: string){
    this.publicationService.getPDF(id);
  }

  accept(id: string){
    this.publicationService.accept(id);
  }

  reject(id: string){
    this.publicationService.reject(id);
  }

  revisionNeeded(id: string){
    this.publicationService.revisionNeeded(id);
  }

}
