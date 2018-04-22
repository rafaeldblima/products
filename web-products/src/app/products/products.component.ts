import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatSort, MatTableDataSource } from '@angular/material';
import { ProductControllerService } from '../connector';
import * as model from './../connector/model/models';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.less']
})
export class ProductsComponent implements OnInit, AfterViewInit {

  displayedColumns = ['descricao', 'preco', 'origem', 'dataCompra', 'categoria', 'actions'];
  products: model.Product[];
  dataSource;

  @ViewChild(MatSort) sort: MatSort;

  constructor(private productService: ProductControllerService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.productService.getAllProductUsingGET()
      .subscribe((resp: model.Product[]) => {
        this.products = resp;
        this.dataSource = new MatTableDataSource<model.Product>(this.products);
        this.dataSource.sort = this.sort;
      });
  }

  ngAfterViewInit() {
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
    this.router.navigate([id, 'edit'], {relativeTo: this.route});
  }

  deleteItem(id: number) {
    this.productService.deleteProductUsingDELETE(id)
      .subscribe((resp: model.Product) => {
        console.log(resp);
      });
  }
}
