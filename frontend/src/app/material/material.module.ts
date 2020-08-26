import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatButtonModule } from '@angular/material/button';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatDialogModule } from '@angular/material/dialog';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule, MatIcon } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatMenuModule } from '@angular/material/menu';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatSliderModule } from '@angular/material/slider';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatSortModule } from '@angular/material/sort';
import { MatTableModule } from '@angular/material/table';
import { MatTabsModule } from '@angular/material/tabs';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { FormsModule } from '@angular/forms';
import { ToastrModule } from 'ngx-toastr';
import { FlexLayoutModule } from '@angular/flex-layout';

const MaterialComponents = [
    MatButtonModule,
    MatButtonToggleModule,
    MatCheckboxModule,
    MatInputModule,
    MatIconModule,
    MatToolbarModule,
    MatMenuModule,
    MatTableModule,
    MatSlideToggleModule,
    MatCardModule,
    MatTooltipModule,
    MatPaginatorModule,
    MatSelectModule,
    MatDatepickerModule,
    MatAutocompleteModule,
    MatDialogModule,
    MatListModule,
    MatTabsModule,
    MatSnackBarModule,
    MatSidenavModule,
    MatFormFieldModule,
    MatSliderModule,
    MatGridListModule,
    MatExpansionModule,
    MatSortModule,
    MatRadioModule,
    FlexLayoutModule,
    ToastrModule.forRoot({ positionClass: 'inline' }),
    FormsModule
  ];

@NgModule({
    imports: [MaterialComponents],
    exports: [MaterialComponents]
  })
  export class MaterialModule { }
