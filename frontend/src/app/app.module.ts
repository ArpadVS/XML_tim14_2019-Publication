import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule  } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';


import { AuthenticationModule } from './authentication/authentication.module';
import { MaterialModule } from './material/material.module';
import { BootstrapModule } from './bootstrap/bootstrap.module';
import { ToastrModule } from 'ngx-toastr';
import { MainModule } from './main/main.module';
import { HttpInterceptorService } from './shared/services/http-interceptor.service';
import { AllPublicationsComponent } from './publications/all-publications/all-publications.component';
import { AddPublicationComponent } from './publications/add-publication/add-publication.component';
import { AddReviewComponent } from './reviews/add-review/add-review.component';
import { MyPublicationsComponent } from './publications/my-publications/my-publications.component';
import { ReviewPublicationsComponent } from './publications/review-publications/review-publications.component';
import { AddExpertiseComponent } from './add-expertise/add-expertise.component';
import { AddRevisionComponent } from './add-revision/add-revision.component';

@NgModule({
  declarations: [
    AppComponent,
    AllPublicationsComponent,
    AddPublicationComponent,
    AddReviewComponent,
    MyPublicationsComponent,
    ReviewPublicationsComponent,
    AddExpertiseComponent,
    AddRevisionComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MaterialModule,
    BootstrapModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    ToastrModule.forRoot({
      progressBar: true,
      timeOut: 4000,
      closeButton: true,
      positionClass: 'toast-bottom-right',
      preventDuplicates: true
    }),
    MaterialModule,
    BootstrapModule,
    AuthenticationModule,
    MainModule,
    BrowserAnimationsModule
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorService, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
