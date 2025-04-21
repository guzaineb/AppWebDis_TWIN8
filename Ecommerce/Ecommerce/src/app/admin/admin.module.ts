import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AdminRoutingModule } from './admin-routing.module';
import { TestComponent } from './pages/test/test.component';
import { ClientShowComponent } from './pages/client-show/client-show.component';


@NgModule({
  declarations: [
    TestComponent,
    ClientShowComponent
  ],
  imports: [
    CommonModule,
    AdminRoutingModule
  ]
})
export class AdminModule { }
