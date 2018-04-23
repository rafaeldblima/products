import { Component, OnChanges, OnInit, ViewChild } from '@angular/core';
import { MatSnackBar, MatSort, MatTableDataSource } from '@angular/material';
import { CategoryControllerService, ProductControllerService } from '../connector';
import * as model from './../connector/model/models';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductsService } from './products.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.less']
})
export class ProductsComponent implements OnInit, OnChanges {

  displayedColumns = ['descricao', 'preco', 'origem', 'dataCompra', 'categoria', 'actions'];
  products: model.Product[];
  dataSource;
  event: boolean;

  @ViewChild(MatSort) sort: MatSort;

  constructor(private productService: ProductControllerService,
              private categoriaService: CategoryControllerService,
              private router: Router,
              private route: ActivatedRoute,
              private service: ProductsService,
              public snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.service.changed.subscribe((event) => {
      this.event = event;
      this.ngOnChanges();
    });
    this.productService.getAllProductUsingGET()
      .subscribe((resp: model.Product[]) => {
        this.products = resp;
        this.dataSource = new MatTableDataSource<model.Product>(this.products);
        this.dataSource.sort = this.sort;
      }, err => console.log(err));
    this.categoriaService.getAllCategoriesUsingGET()
      .subscribe((resp: model.Category[]) => {
        this.service.setCategorias(resp);
      }, err => console.log(err));
  }

  ngOnChanges() {
    console.log(this.event);
    if (this.event) {
      this.productService.getAllProductUsingGET()
        .subscribe((resp: model.Product[]) => {
            this.products = resp;
            console.log(resp);
            this.dataSource.filter = '';
            this.dataSource = new MatTableDataSource<model.Product>(this.products);
            this.dataSource.sort = this.sort;
          },
          err => console.log(err),
          () => this.service.changed.emit(false));
    }
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim();
    filterValue = filterValue.toLowerCase();
    this.dataSource.filter = filterValue;
  }

  addNew() {
    this.router.navigate(['new'], {relativeTo: this.route});
  }

  startEdit(id: number) {
    this.productService.getBookByIdUsingGET(id).subscribe((resp: model.Product) => {
        this.service.setProduct(resp);
      }, err => console.log(err),
      () => {
        this.router.navigate([id, 'edit'], {relativeTo: this.route});
      });
  }

  deleteItem(id: number) {
    this.productService.deleteProductUsingDELETE(id)
      .subscribe((resp: model.Product) => {
          console.log(resp);
        },
        err => console.log(err),
        () => this.snackBar.open('Deletado!', 'x', {
          duration: 1500
        }));
    const index = this.products.findIndex(prod => prod.id === id);
    this.products.splice(index, 1);
    this.dataSource.filter = '';
    this.dataSource = new MatTableDataSource<model.Product>(this.products);
  }
}
