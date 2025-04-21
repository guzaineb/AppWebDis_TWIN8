import { Component, OnInit } from '@angular/core';
import { Produit } from '../../models/produit';
import { Category } from '../../models/category';
import { ActivatedRoute, Router } from '@angular/router';
import { ProduitService } from '../product.service';
import { CategoryService } from 'src/app/category/category.service';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.css']
})
export class ProduitFormComponent implements OnInit {
  produit: Produit = {
    nom: '',
    description: '',
    quantiteDisponible: 0,
    prix: 0,
    images: [],
    category: undefined
  };

  categories: Category[] = [];
  selectedImages: { file: File, url: string }[] = [];
  existingImages: string[] = [];
  isEditMode = false;
  isLoading = false;
  errorMessage = '';

  constructor(
    private produitService: ProduitService,
    private categoryService: CategoryService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadCategories();
    this.checkEditMode();
  }

  private loadCategories(): void {
    this.categoryService.getAllCategories().subscribe({
      next: (data) => this.categories = data,
      error: (err) => {
        console.error('Error loading categories:', err);
        this.errorMessage = 'Failed to load categories';
      }
    });
  }

  private checkEditMode(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.loadProduit(+id);
    }
  }

  private loadProduit(id: number): void {
    this.isLoading = true;
    this.produitService.getProduitById(id).subscribe({
      next: (produit) => {
        this.produit = produit;
        this.existingImages = produit.images || [];
        this.isLoading = false;
      },
      error: (err) => {
        console.error('Error loading product:', err);
        this.errorMessage = 'Failed to load product';
        this.isLoading = false;
      }
    });
  }

  onFileChange(event: any): void {
    const files: FileList = event.target.files;
    for (let i = 0; i < files.length; i++) {
      const file = files[i];
      if (!file.type.startsWith('image/')) continue;

      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.selectedImages.push({ file, url: e.target.result });
      };
      reader.readAsDataURL(file);
    }
  }

  removeSelectedImage(index: number): void {
    this.selectedImages.splice(index, 1);
  }

  removeExistingImage(index: number): void {
    this.existingImages.splice(index, 1);
  }

  onSubmit(): void {
    if (this.isLoading) return;
    this.isLoading = true;
    this.errorMessage = '';

    const formData = this.prepareFormData();

    if (this.isEditMode) {
      this.updateProduit(formData);
    } else {
      this.createProduit(formData);
    }
  }

  private prepareFormData(): FormData {
    const formData = new FormData();
    
    // Append product data as JSON
    const produitData = {
      nom: this.produit.nom,
      description: this.produit.description,
      prix: this.produit.prix,
      quantiteDisponible: this.produit.quantiteDisponible,
      categoryId: this.produit.category?.id,
      existingImages: this.existingImages
    };

    formData.append('produit', JSON.stringify(produitData));

    // Append new images
    this.selectedImages.forEach(img => {
      formData.append('images', img.file);
    });

    return formData;
  }

  private createProduit(formData: FormData): void {
    this.produitService.createProduit(formData).subscribe({
      next: () => this.handleSuccess(),
      error: (err) => this.handleError(err)
    });
  }

  private updateProduit(formData: FormData): void {
    if (!this.produit.id) return;
    
    this.produitService.updateProduit(this.produit.id, formData).subscribe({
      next: () => this.handleSuccess(),
      error: (err) => this.handleError(err)
    });
  }

  private handleSuccess(): void {
    this.isLoading = false;
    this.router.navigate(['/produits']);
  }

  private handleError(error: any): void {
    console.error('Error:', error);
    this.isLoading = false;
    this.errorMessage = error.error?.message || 'An error occurred. Please try again.';
  }
}