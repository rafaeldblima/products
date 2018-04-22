package com.rafaeldbl.wsproducts.controller;

import com.rafaeldbl.wsproducts.model.Product;
import com.rafaeldbl.wsproducts.service.IProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product/")
@Api(value = "Product Controller API", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    private final IProductService service;

    @Autowired
    public ProductController(IProductService service) {
        this.service = service;
    }


    @RequestMapping(path = "", method = RequestMethod.GET)
    @ApiOperation("Get all products")
    public List<Product> getAllProduct() {
        return service.getAllProducts();
    }

    @RequestMapping(path = "id/{id}", method = RequestMethod.GET)
    @ApiOperation("Get product by id")
    public Product getBookById(@PathVariable long id) throws NotFoundException {
        return service.getById(id);
    }

    @RequestMapping(path = "allByCategory/id/{id}", method = RequestMethod.GET)
    @ApiOperation("Get products by category id")
    public List<Product> getAllByCategoryId(@PathVariable long id) {
        return service.getAllProductsByCategoryId(id);
    }

    @RequestMapping(path = "newProduct", method = RequestMethod.POST)
    @ApiOperation("Create new Product")
    public Product createProduct(@RequestBody Product product) throws Exception {
        return service.createProduct(product);
    }

    @RequestMapping(path = "updateProduct", method = RequestMethod.PUT)
    @ApiOperation("Update product")
    public Product updateProduct(@RequestBody Product product) throws NotFoundException {
        return service.updateProduct(product);
    }

    @RequestMapping(path = "deleteProduct/id/{id}", method = RequestMethod.DELETE)
    @ApiOperation("Delete Product")
    public Product deleteProduct(@PathVariable long id) throws NotFoundException {
        return service.deleteProduct(id);
    }
}
