import { Component } from '@angular/core';
import { ProduitService } from '../product.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Produit } from 'src/app/models/produit';

@Component({
  selector: 'app-product-admin',
  templateUrl: './product-admin.component.html',
  styleUrls: ['./product-admin.component.css']
})
export class ProductAdminComponent {
  produits: Produit[] = [];

  constructor(
    private produitService: ProduitService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const categoryId = this.route.snapshot.paramMap.get('categoryId');
    if (categoryId) {
      this.produitService.getAllProduits().subscribe(data => {
        this.produits = data.filter(p => p.category?.id === +categoryId);
      });
    } else {
      this.produitService.getAllProduits().subscribe(data => {
        this.produits = data;
      });
    }
  } editProduit(id: number): void {
    this.router.navigate(['/produit/edit/', id]);
  }

  deleteProduit(id: number): void {
    this.produitService.deleteProduit(id).subscribe(() => {
      this.produits = this.produits.filter(p => p.id !== id);
    });
  }

}
