import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './authentication/login/login.component';
import { AllPublicationsComponent } from './publications/all-publications/all-publications.component';
import { AddPublicationComponent } from './publications/add-publication/add-publication.component';
import { RoleGuard } from './shared/guards/role.guard';
import { AddReviewComponent } from './reviews/add-review/add-review.component';
import { MyPublicationsComponent } from './publications/my-publications/my-publications.component';
import { ReviewPublicationsComponent } from './publications/review-publications/review-publications.component';
import { AddExpertiseComponent } from './add-expertise/add-expertise.component';


const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'publications/all', component: AllPublicationsComponent},
  {
    path: 'publications/add',
    component: AddPublicationComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_AUTHOR|ROLE_REVIEWER|ROLE_EDITOR'}
  },
  {
    path: 'reviews/add',
    component: AddReviewComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_REVIEWER|ROLE_EDITOR'}
  },
  {
    path: 'publications/my',
    component: MyPublicationsComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_AUTHOR|ROLE_REVIEWER|ROLE_EDITOR'}
  },
  {
    path: 'publications/reviewTable',
    component: ReviewPublicationsComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_REVIEWER|ROLE_EDITOR'}
  },
  {
    path: 'expertise/add',
    component: AddExpertiseComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_AUTHOR|ROLE_REVIEWER|ROLE_EDITOR'}
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
