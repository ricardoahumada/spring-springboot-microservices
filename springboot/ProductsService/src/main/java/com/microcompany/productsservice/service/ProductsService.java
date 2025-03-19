package com.microcompany.productsservice.service;

import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.persistence.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    @PersistenceContext
    EntityManager em;

    public List<Product> getProductsByText(String text) {
        return productsRepository.findByNameContaining(text);
    }

    public Product duplicate(Long pid) {
        Product prod = productsRepository.findById(pid).get();
        em.detach(prod);
        prod.setId(null);
        return  productsRepository.save(prod);
    }
}
