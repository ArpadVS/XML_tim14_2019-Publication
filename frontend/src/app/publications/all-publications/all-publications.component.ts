import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { Publication } from 'src/app/models/publication.model';
import { PublicationService } from '../service/publication.service';

@Component({
  selector: 'app-all-publications',
  templateUrl: './all-publications.component.html',
  styleUrls: ['./all-publications.component.scss']
})
export class AllPublicationsComponent implements OnInit {

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
    this.publicationService.getAll().subscribe((res: Publication[]) => {
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
    return ['Index', 'Author', 'Title', 'HTML', 'PDF'];
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
}
