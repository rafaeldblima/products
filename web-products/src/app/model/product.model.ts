import { Product } from '../connector/model/product';
import { CategoryModel } from './category.model';

export class ProductModel implements Product {
  constructor(descricao, preco, categoria, origem, dataCompra) {
    this.descricao = descricao;
    this.preco = preco;
    this.categoria = categoria;
    this.origem = origem;
    this.dataCompra = dataCompra;
  }
  categoria?: CategoryModel;
  dataCompra?: Date;
  descricao?: string;
  id?: number;
  origem?: string;
  preco?: number;
}
