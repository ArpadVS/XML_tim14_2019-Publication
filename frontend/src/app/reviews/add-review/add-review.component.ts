import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ReviewService } from '../service/review.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.scss']
})
export class AddReviewComponent implements OnInit {
  addReviewForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private reviewService: ReviewService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.addReviewForm = this.formBuilder.group({
      review: ['', [Validators.required]]
    });
  }

  onAddReviewSubmit(){
    console.log('ON ADD review');
    const review: string = this.addReviewForm.get('review').value;
    this.reviewService.add(review);
  }
}
