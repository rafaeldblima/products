import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as model from './../../connector/model/models';
import { CategoryControllerService, ProductControllerService } from '../../connector';
import { ProductModel } from '../../model/product.model';
import { CategoryModel } from '../../model/category.model';

@Component({
  selector: 'app-product-form',
  templateUrl: './product-form.component.html',
  styleUrls: ['./product-form.component.less']
})
export class ProductFormComponent implements OnInit {

  id: number;
  editMode = false;
  produto: ProductModel;
  productForm: FormGroup;
  categorias: CategoryModel[];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private productService: ProductControllerService,
              private categoriaService: CategoryControllerService) {
  }

  ngOnInit() {
    this.route.params.subscribe((params: Params) => {
      this.id = params['id'];
      this.editMode = params['id'] != null;
      this.initForm();
    });
    this.categoriaService.getAllCategoriesUsingGET()
      .subscribe((resp: model.Category[]) => {
        this.categorias = resp;
      });
  }

  private initForm() {
    this.produto = new ProductModel(
      '', 0, new CategoryModel(), 'Nacional', new Date()
    );
    if (this.editMode) {
      this.productService.getBookByIdUsingGET(this.id).subscribe((resp: model.Product) => {
        this.produto = resp;
      });
    }

    this.productForm = new FormGroup({
      'descricao': new FormControl(this.produto.descricao, Validators.required),
      'preco': new FormControl(this.produto.preco, Validators.required),
      'categoria': new FormControl(this.produto.categoria.id, Validators.required),
      'origem': new FormControl(this.produto.origem, Validators.required)
    });
  }

  onCancel() {
    this.router.navigate(['../'], {relativeTo: this.route});
  }

  onSubmit() {
    let category = new CategoryModel();
    category.id = this.productForm.value['categoria'];
    const product = new ProductModel(
      this.productForm.value['descricao'],
      this.productForm.value['preco'],
      category,
      this.productForm.value['origem'],
      this.produto.dataCompra
    );
    if (this.editMode) {
      this.productService.updateProductUsingPUT(product)
        .subscribe((resp: model.Product) => {
          console.log(resp);
        });
    } else {
      this.productService.createProductUsingPOST(product)
        .subscribe((resp: model.Product) => {
          console.log(resp);
        });
    }
  }
}
