package com.rafaeldbl.wsproducts.service;

import com.rafaeldbl.wsproducts.builder.CategoryBuilder;
import com.rafaeldbl.wsproducts.builder.ProductBuilder;
import com.rafaeldbl.wsproducts.model.Category;
import com.rafaeldbl.wsproducts.model.Product;
import com.rafaeldbl.wsproducts.repository.ProductRepository;
import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @InjectMocks
    private ProductService service;

    @Mock
    private ProductRepository repository;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetAllProducts() {
        List<Product> products = Collections.singletonList(ProductBuilder.oneProduct().now());

        when(repository.findAll()).thenReturn(products);

        assertEquals(products, service.getAllProducts());
    }

    @Test
    public void shouldGetProductByCategoryId() {
        Category category = CategoryBuilder.oneCategory().withId(1L).now();
        List<Product> products = Collections.singletonList(ProductBuilder.oneProduct().withCategoria(category).now());

        when(repository.findAllByCategoriaId(category.getId())).thenReturn(products);

        assertEquals(products, service.getAllProductsByCategoryId(category.getId()));
    }

    @Test
    public void shouldCreateAProduct() throws Exception {
        Category category = CategoryBuilder.oneCategory().now();
        Date date = Calendar.getInstance().getTime();
        Product product = ProductBuilder.oneProduct().withDataCompra(date).withCategoria(category).now();

        when(repository.save(product)).thenReturn(product);

        assertEquals(product, service.createProduct(product));
    }

    @Test
    public void shouldThrowExceptionWhenProductAlreadyExistsWithSameDescricaoAndCategoria() throws Exception {
        Category category = CategoryBuilder.oneCategory().now();
        Date date = Calendar.getInstance().getTime();
        Product product = ProductBuilder.oneProduct().withDescricao("Teste").withDataCompra(date)
                .withCategoria(category).now();

        when(repository.findAllByDescricao(product.getDescricao())).thenReturn(Collections.singletonList(product));

        exception.expect(Exception.class);
        exception.expectMessage("Já existe um produto com essa descrição nessa categoria.");

        service.createProduct(product);
    }

    @Test
    public void shouldFindProductById() throws NotFoundException {
        Product product = ProductBuilder.oneProduct().withId(1L).now();

        when(repository.findById(1L)).thenReturn(java.util.Optional.ofNullable(product));

        assertEquals(product, service.getById(1L));
    }

    @Test
    public void shouldThrowExceptionWhenProductsNotPresent() throws NotFoundException {
        exception.expect(NotFoundException.class);
        exception.expectMessage("Produto não encontrado!");

        service.deleteProduct(1L);
    }

    @Test
    public void shouldUpdateProduct() throws NotFoundException {
        Product productInDb = ProductBuilder.oneProduct().now();
        Product product = ProductBuilder.oneProduct().now();

        when(repository.findById(product.getId())).thenReturn(Optional.ofNullable(productInDb));
        when(repository.save(product)).thenReturn(product);

        assertEquals(product, service.updateProduct(product));
    }

    @Test
    public void shouldThrowExceptionIsProductDoesNotExistsWhenUpdating() throws NotFoundException {
        Product product = ProductBuilder.oneProduct().now();

        exception.expect(NotFoundException.class);
        exception.expectMessage("Produto não encontrado!");

        service.updateProduct(product);
    }

    @Test
    public void shouldDeleteAProduct() throws NotFoundException {
        Product product = ProductBuilder.oneProduct().withId(1L).now();

        when(repository.findById(1L)).thenReturn(Optional.ofNullable(product));

        assertEquals(product, service.deleteProduct(1L));
    }

    @Test
    public void shouldThrowExceptionIsProductDoesNotExistsWhenDeleting() throws NotFoundException {
        exception.expect(NotFoundException.class);
        exception.expectMessage("Produto não encontrado!");

        service.deleteProduct(1L);
    }
}
