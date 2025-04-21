import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { HomeComponent } from './home/home.component';
import { TestComponent } from './test/test.component';

import { ProductDetailComponent } from './product/product-detail/product-detail.component';
import { ProductAdminComponent } from './product/product-admin/product-admin.component';
import { HttpClientModule } from '@angular/common/http';
import { CategoryListComponent } from './category/category-list/category-list.component';
import { AddCategoryComponent } from './category/add-category/add-category.component';

import { ProductListComponent } from './product/product-list/product-list.component';
import { ProduitFormComponent } from './product/product-form/product-form.component';
import { FormsModule } from '@angular/forms';
import { ProductCardComponent } from './product/product-card/product-card.component';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    HomeComponent,
    TestComponent,
    CategoryListComponent,
    AddCategoryComponent,
    ProduitFormComponent,
    ProductListComponent,
    ProductDetailComponent,
    ProductAdminComponent
    ,ProductCardComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    
],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
