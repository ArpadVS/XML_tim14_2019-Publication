import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from '../authentication/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-expertise',
  templateUrl: './add-expertise.component.html',
  styleUrls: ['./add-expertise.component.scss']
})
export class AddExpertiseComponent implements OnInit {

  addExpertiseForm: FormGroup;
  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.addExpertiseForm = this.formBuilder.group({
      'expertise': ['', [
        Validators.required
      ]]
    });
  }

  onAddExpertiseSubmit() {
    const expertise: string = this.addExpertiseForm.get('expertise').value;
    this.authService.addExpertise(expertise);
  }
}
