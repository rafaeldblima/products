package com.rafaeldbl.wsproducts.builder;

import com.rafaeldbl.wsproducts.model.Category;


public class CategoryBuilder {
    private Category elemento;

    private CategoryBuilder() {
    }

    public static CategoryBuilder oneCategory() {
        CategoryBuilder builder = new CategoryBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    private static void inicializarDadosPadroes(CategoryBuilder builder) {
        builder.elemento = new Category();
        Category elemento = builder.elemento;


        elemento.setId(0L);
        elemento.setDescricao("");
    }

    public CategoryBuilder withId(long param) {
        elemento.setId(param);
        return this;
    }

    public CategoryBuilder withDescricao(String param) {
        elemento.setDescricao(param);
        return this;
    }

    public Category now() {
        return elemento;
    }
}