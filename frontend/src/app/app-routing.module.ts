import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './authentication/login/login.component';
import { AllPublicationsComponent } from './publications/all-publications/all-publications.component';
import { AddPublicationComponent } from './publications/add-publication/add-publication.component';
import { RoleGuard } from './shared/guards/role.guard';


const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'publications/all', component: AllPublicationsComponent},
  {
    path: 'publications/add',
    component: AddPublicationComponent,
    canActivate: [RoleGuard],
    data: {expectedRoles: 'ROLE_AUTHOR|ROLE_REVIEWER|ROLE_EDITOR'}
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
