import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { TestComponent } from './test/test.component';
import { CategoryListComponent } from './category/category-list/category-list.component';
import { ProductListComponent } from './product/product-list/product-list.component';
import {  ProduitFormComponent } from './product/product-form/product-form.component';
import { ProductDetailComponent } from './product/product-detail/product-detail.component';
import { ProductAdminComponent } from './product/product-admin/product-admin.component';
import { AppComponent } from './app.component';

const routes: Routes = [
  {
    path: '',component:HomeComponent
  },
  { path: 'categories', component: CategoryListComponent },
  { path: 'produits/:id', component: ProductListComponent },
  { 
    path: 'produit/form', // Chemin absolu
    component: ProduitFormComponent 
  },
  { 
    path: 'produit/edit/:id', // Pour l'édition
    component: ProduitFormComponent
  },
  { path: 'produitDetails/:id', component: ProductDetailComponent },

  { path: 'products/:id/quantity', component: ProduitFormComponent },
  { path: 'products/Admin', component: ProductAdminComponent },

  { path: '', redirectTo: '/categories', pathMatch: 'full' }

  ,{
    path: 'test',component:TestComponent
  },
  {
    path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule)
  },
  {
    path: 'user', loadChildren: () => import('./user/user.module').then(m => m.UserModule)
  },

     ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  bootstrap: [AppComponent],
})
export class AppRoutingModule { }
