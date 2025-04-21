import { Category } from "./category";

export interface Produit {
  id?: number;
  nom: string;
  description: string;
  prix: number;
  quantiteDisponible: number;
  images: string[];
  category?: Category; // Optional category
}