import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Role } from './guards/role.guard/role.guard.component';



@NgModule({
  declarations: [Role.GuardComponent],
  imports: [
    CommonModule
  ]
})
export class SharedModule { }
