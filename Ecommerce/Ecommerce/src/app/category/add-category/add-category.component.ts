import { Component } from '@angular/core';
import { Category } from 'src/app/models/category';
import { CategoryService } from '../category.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-category',
  templateUrl: './add-category.component.html',
  styleUrls: ['./add-category.component.css']
})
export class AddCategoryComponent {
  category: Category = { name: '', codeApi: '' };

  constructor(private categoryService: CategoryService, private router: Router) {}

  onSubmit(): void {
    this.categoryService.createCategory(this.category).subscribe(() => {
      this.router.navigate(['/categories']);
    });
  }

}
