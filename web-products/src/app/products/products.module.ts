import { NgModule } from '@angular/core';
import { ProductsComponent } from './products.component';
import { CoreModule } from '../core/core.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { ProductFormComponent } from './product-form/product-form.component';
import { MaterialModule } from '../core/material.module';
import { CategoryControllerService } from '../connector';

@NgModule({
  imports: [
    CoreModule,
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    MaterialModule,
    ReactiveFormsModule
  ],
  declarations: [ProductsComponent, ProductFormComponent]
})
export class ProductsModule {
}
