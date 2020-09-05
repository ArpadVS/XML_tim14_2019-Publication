import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

import { Router } from '@angular/router';
import { ReviewService } from '../reviews/service/review.service';

@Component({
  selector: 'app-add-revision',
  templateUrl: './add-revision.component.html',
  styleUrls: ['./add-revision.component.scss']
})
export class AddRevisionComponent implements OnInit {

  addRevisionForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private reviewService: ReviewService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.addRevisionForm = this.formBuilder.group({
      revision: ['', [Validators.required]],
      paper_id: ['', [Validators.required]],
    });
  }

  onAddRevisionSubmit(){
    console.log('ON ADD revision');
    const revision: string = this.addRevisionForm.get('revision').value;
    const id: string = this.addRevisionForm.get('paper_id').value;
    this.reviewService.addRevision(revision, id);
  }

}
