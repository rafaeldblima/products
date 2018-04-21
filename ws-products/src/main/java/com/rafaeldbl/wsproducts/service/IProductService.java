package com.rafaeldbl.wsproducts.service;

import com.rafaeldbl.wsproducts.model.Product;
import javassist.NotFoundException;

import java.util.List;

public interface IProductService {
    List<Product> getAllProducts();

    Product getById(long id) throws NotFoundException;

    List<Product> getAllProductsByCategoryId(long id);

    Product createProduct(Product product) throws Exception;

    Product updateProduct(Product product) throws NotFoundException;

    Product deleteProduct(long id) throws NotFoundException;
}
