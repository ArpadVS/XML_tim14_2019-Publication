import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PublicationService } from '../service/publication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-publication',
  templateUrl: './add-publication.component.html',
  styleUrls: ['./add-publication.component.scss']
})
export class AddPublicationComponent implements OnInit {
  addScientificPaperForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private publicationService: PublicationService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.addScientificPaperForm = this.formBuilder.group({
      scientific_paper: ['', [Validators.required]],
      cover_letter: ['', [Validators.required]]
    });
  }

  onAddScientificPaperSubmit(){
    console.log('ON ADD PAPER');
    const scientificPaper: string = this.addScientificPaperForm.get('scientific_paper').value;
    const coverLetter: string = this.addScientificPaperForm.get('cover_letter').value;
    this.publicationService.add(scientificPaper);
  }

}
