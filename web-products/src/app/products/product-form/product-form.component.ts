import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import * as model from './../../connector/model/models';
import { CategoryControllerService, ProductControllerService } from '../../connector';
import { ProductModel } from '../../model/product.model';
import { CategoryModel } from '../../model/category.model';
import { ProductsService } from '../products.service';
import { MatSnackBar } from '@angular/material';

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
  title: string;
  categorias: model.Category[];

  constructor(private route: ActivatedRoute,
              private router: Router,
              private productService: ProductControllerService,
              private service: ProductsService,
              public snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.categorias = this.service.getCategorias();
    this.route.params.subscribe((params: Params) => {
      this.id = params['id'];
      this.editMode = params['id'] != null;
      if (this.editMode) {
        this.title = 'Editar';
        this.produto = this.service.getProduct();
      } else {
        this.produto = new ProductModel(
          '', 0, new CategoryModel(), 'Nacional', new Date()
        );
        this.title = 'Adicionar';
      }
      this.initForm();
    });
  }

  private initForm() {
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
    const category = new CategoryModel();
    category.id = this.productForm.value['categoria'];
    const product = new ProductModel(
      this.productForm.value['descricao'],
      this.productForm.value['preco'],
      category,
      this.productForm.value['origem'],
      this.produto.dataCompra
    );
    product.id = this.id;
    console.log(this.produto);
    if (this.editMode) {
      this.productService.updateProductUsingPUT((product))
        .subscribe((resp: model.Product) => {
            console.log(resp);
          }, err => console.log(err),
          () => this.onComplete());
    } else {
      this.productService.createProductUsingPOST((product))
        .subscribe((resp: model.Product) => {
            console.log(resp);
          }, err => console.log(err),
          () => this.onComplete());
    }
  }

  onComplete() {
    this.snackBar.open('Salvo com sucesso!', 'x', {
      duration: 1500
    });
    this.service.changed.emit(true);
    this.router.navigate(['products']);
  }
}
