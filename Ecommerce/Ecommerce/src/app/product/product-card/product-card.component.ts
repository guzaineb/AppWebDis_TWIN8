
import { Component, Input } from "@angular/core"

import { CommonModule, CurrencyPipe } from "@angular/common"; 
import { ActivatedRoute, Router, RouterLink, RouterModule } from "@angular/router";
import { ReactiveFormsModule } from "@angular/forms";
import { Produit } from "src/app/models/produit";
import { CategoryService } from "src/app/category/category.service";
import { ProduitService } from "../product.service";
@Component({
  selector: 'app-product-card',
  
  templateUrl: './product-card.component.html',
    styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent {
  produits: Produit[] = [];
  filteredProduits: Produit[] = [];
  isLoading = true;
  searchTerm = '';
  minPrice?: number;
  maxPrice?: number;
  inStockOnly = false;

  constructor(
    private produitService: ProduitService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadAllProduits();
  }

  private loadAllProduits(): void {
    this.isLoading = true;
    this.produitService.getAllProduits().subscribe({
      next: produits => {
        this.produits = produits;
        this.filteredProduits = [...produits];
        this.isLoading = false;
      },
      error: error => {
        console.error('Erreur lors de la récupération des produits :', error);
        this.isLoading = false;
      }
    });
  }

  searchByName(): void {
    if (!this.searchTerm.trim()) {
      this.filteredProduits = [...this.produits];
      return;
    }

    this.isLoading = true;
    this.produitService.getProduitByNom(this.searchTerm).subscribe({
      next: produits => {
        this.filteredProduits = produits;
        this.isLoading = false;
      },
      error: error => {
        console.error('Erreur recherche par nom:', error);
        this.isLoading = false;
      }
    });
  }

  searchByPrice(): void {
    if (!this.minPrice || !this.maxPrice) {
      alert('Veuillez remplir les deux champs de prix');
      return;
    }

    this.isLoading = true;
    this.produitService.getProduitsByPrixRange(this.minPrice, this.maxPrice).subscribe({
      next: produits => {
        this.filteredProduits = produits;
        this.isLoading = false;
      },
      error: error => {
        console.error('Erreur recherche par prix:', error);
        this.isLoading = false;
      }
    });
  }

  toggleStockFilter(): void {
    this.isLoading = true;
    if (this.inStockOnly) {
      this.produitService.getProduitsEnStock().subscribe({
        next: produits => {
          this.filteredProduits = produits;
          this.isLoading = false;
        },
        error: error => {
          console.error('Erreur recherche en stock:', error);
          this.isLoading = false;
        }
      });
    } else {
      this.filteredProduits = [...this.produits];
      this.isLoading = false;
    }
  }

  handleImageError(event: Event): void {
    (event.target as HTMLImageElement).src = 'assets/default-product.png';
  }

  addToCart(produit: Produit): void {
    // Implémentez la logique d'ajout au panier
  }
}