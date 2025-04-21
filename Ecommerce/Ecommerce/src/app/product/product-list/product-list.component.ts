import { Component, OnInit } from '@angular/core';
import { ProduitService } from '../product.service';
import { Produit } from 'src/app/models/produit';
import { ActivatedRoute, Router } from '@angular/router';
import { CategoryService } from 'src/app/category/category.service';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})

 
export class ProductListComponent implements OnInit {
  produits: Produit[] = [];
  categoryId!: number;
  isLoading = true;

  constructor(
    private categoryService: CategoryService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.categoryId = +params['id']; 
      this.loadProduits();
    });
  }

  private loadProduits(): void {
    this.isLoading = true;
    this.categoryService.getProduitsByCategory(this.categoryId)
      .subscribe({
        next: (produits) => {
          this.produits = produits;
          this.isLoading = false;
        },
        error: (err) => {
          console.error('Erreur de chargement', err);
          this.isLoading = false;
        }
      });
  }


  addToCart(produit: Produit): void {
    // Implémentez la logique d'ajout au panier
  }

  
  

}
