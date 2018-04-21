package com.rafaeldbl.wsproducts.service;

import com.rafaeldbl.wsproducts.builder.CategoryBuilder;
import com.rafaeldbl.wsproducts.model.Category;
import com.rafaeldbl.wsproducts.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    @InjectMocks
    private CategoryService service;

    @Mock
    private CategoryRepository repository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetAllCategories() {
        List<Category> categories = Collections.singletonList(CategoryBuilder.oneCategory().now());

        when(repository.findAll()).thenReturn(categories);

        Assert.assertEquals(categories, service.getAllCategories());
    }
}
