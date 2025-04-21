import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientShowComponent } from './pages/client-show/client-show.component';
import { TestComponent } from './pages/test/test.component';

const routes: Routes = [
  {
    path: 'client', component: ClientShowComponent
  },
  {
    path:'test', component: TestComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
