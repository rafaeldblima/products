package com.rafaeldbl.wsproducts.controller;

import com.rafaeldbl.wsproducts.model.Category;
import com.rafaeldbl.wsproducts.service.ICategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/category/")
@Api(value = "Category Controller API", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private final ICategoryService service;

    @Autowired
    public CategoryController(ICategoryService service) {
        this.service = service;
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    @ApiOperation("Get all categories")
    public List<Category> getAllCategories() {
        return service.getAllCategories();
    }

}
