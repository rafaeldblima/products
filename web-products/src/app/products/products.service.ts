import { EventEmitter, Injectable, Output } from '@angular/core';
import { ProductControllerService } from '../connector';
import * as model from '../connector/model/models';

@Injectable()
export class ProductsService {
  @Output() changed: EventEmitter<boolean> = new EventEmitter();

  product: model.Product;
  categorias: model.Category[];

  constructor(private productService: ProductControllerService) {
  }

  setProduct(resp: model.Product) {
    this.product = resp;
  }

  getProduct(): model.Product {
    return this.product;
  }

  setCategorias(resp: model.Category[]) {
    this.categorias = resp;
  }

  getCategorias(): model.Category[] {
    return this.categorias
  }
}
