import { Component, OnInit } from '@angular/core';
import { Category } from '../../models/category';
import { Router } from '@angular/router';
import { CategoryService } from '../category.service';
import { Produit } from '../../models/produit';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {
  categories: Category[] = [];
  produits: Produit[] = [];
  qrImageUrl: string = '';
  selectedCategoryId: number | null = null;
  selectedCategory: Category | null = null;
  isLoading: boolean = false;
  errorMessage: string = '';

  constructor(
    private categoryService: CategoryService, 
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.isLoading = true;
    this.categoryService.getAllCategories().subscribe({
      next: (data) => {
        this.categories = data;
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors du chargement des catégories';
        this.isLoading = false;
        console.error(err);
      }
    });
  }

  viewProductsByCategory(categoryId: number): void {
    this.router.navigate(['/produits', categoryId]);
  }

  generateQrAndProducts(category: Category): void {
    if (!category.id) return;

    this.selectedCategoryId = category.id;
    this.selectedCategory = category;
    this.isLoading = true;
    this.errorMessage = '';

    // Appel au backend pour générer le QR code
    this.categoryService.getQRCodeForCategory(category.id).subscribe({
      next: (blob) => {
        // Création de l'URL à partir du blob reçu
        this.qrImageUrl = URL.createObjectURL(blob);
        this.isLoading = false;
      },
      error: (err) => {
        this.errorMessage = 'Erreur lors de la génération du QR code';
        this.isLoading = false;
        console.error(err);
      }
    });

    // Chargement des produits associés
    this.categoryService.getProduitsByCategory(category.id).subscribe({
      next: (produits) => {
        this.produits = produits;
      },
      error: (err) => {
        console.error('Erreur produits', err);
      }
    });
  }

  // Nettoyage des URLs créées
  ngOnDestroy() {
    if (this.qrImageUrl) {
      URL.revokeObjectURL(this.qrImageUrl);
    }
  }
}