import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { ApiModule, CategoryControllerService, ProductControllerService } from './connector';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CoreModule } from './core/core.module';
import { ProductsModule } from './products/products.module';
import { AppRoutingModule } from './app-routing.module';
import { MaterialModule } from './core/material.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ApiModule,
    CoreModule,
    MaterialModule,
    ProductsModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  exports: [
    BrowserAnimationsModule,
    MaterialModule,
    ApiModule
  ],
  providers: [
    ProductControllerService,
    CategoryControllerService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
