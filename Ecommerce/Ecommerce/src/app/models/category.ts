import { Produit } from './produit';
export interface Category {
  id?: number;
  name: string;
  codeApi: string;
  produits?: Produit[];
}