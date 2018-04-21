package com.rafaeldbl.wsproducts.repository;

import com.rafaeldbl.wsproducts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoriaId(long id);

    List<Product> findAllByDescricao(String descricao);
}
