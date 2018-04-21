package com.rafaeldbl.wsproducts.builder;

import com.rafaeldbl.wsproducts.model.Category;
import com.rafaeldbl.wsproducts.model.Product;

import java.util.Date;

public class ProductBuilder {
    private Product elemento;

    private ProductBuilder() {
    }

    public static ProductBuilder oneProduct() {
        ProductBuilder builder = new ProductBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    private static void inicializarDadosPadroes(ProductBuilder builder) {
        builder.elemento = new Product();
        Product elemento = builder.elemento;


        elemento.setId(0L);
        elemento.setDescricao("");
        elemento.setDataCompra(null);
        elemento.setPreco(0.0);
        elemento.setOrigem("");
        elemento.setCategoria(null);
    }

    public ProductBuilder withId(long param) {
        elemento.setId(param);
        return this;
    }

    public ProductBuilder withDescricao(String param) {
        elemento.setDescricao(param);
        return this;
    }

    public ProductBuilder withDataCompra(Date param) {
        elemento.setDataCompra(param);
        return this;
    }

    public ProductBuilder withPreco(double param) {
        elemento.setPreco(param);
        return this;
    }

    public ProductBuilder withOrigem(String param) {
        elemento.setOrigem(param);
        return this;
    }

    public ProductBuilder withCategoria(Category param) {
        elemento.setCategoria(param);
        return this;
    }

    public Product now() {
        return elemento;
    }
}