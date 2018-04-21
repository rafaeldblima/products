package com.rafaeldbl.wsproducts.service;

import com.rafaeldbl.wsproducts.model.Product;
import com.rafaeldbl.wsproducts.repository.ProductRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("IProductService")
public class ProductService implements IProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public Product getById(long id) throws NotFoundException {
        Optional<Product> product = repository.findById(id);
        checkProduct(product);
        return product.get();
    }

    @Override
    public List<Product> getAllProductsByCategoryId(long id) {
        return repository.findAllByCategoriaId(id);
    }

    @Override
    public Product createProduct(Product product) throws Exception {
        List<Product> allByDescricao = repository.findAllByDescricao(product.getDescricao());
        for (Product productInDb : allByDescricao) {
            if (productInDb.getCategoria().equals(product.getCategoria()))
                throw new Exception("Já existe um produto com essa descrição nessa categoria.");
        }

        return repository.save(product);
    }

    @Override
    public Product updateProduct(Product product) throws NotFoundException {
        Optional<Product> productInDb = repository.findById(product.getId());
        checkProduct(productInDb);
        return repository.save(product);
    }

    @Override
    public Product deleteProduct(long id) throws NotFoundException {
        Optional<Product> product = repository.findById(id);
        checkProduct(product);
        repository.deleteById(id);
        return product.get();
    }

    private void checkProduct(Optional<Product> productInDb) throws NotFoundException {
        if (!productInDb.isPresent())
            throw new NotFoundException("Produto não encontrado!");
    }
}