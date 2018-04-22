import { Category } from '../connector/model/category';

export class CategoryModel implements Category{
  constructor() {}

  descricao?: string;
  id?: number;

}
