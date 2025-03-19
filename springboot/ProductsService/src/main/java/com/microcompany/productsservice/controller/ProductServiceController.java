package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.model.StatusMessage;
import com.microcompany.productsservice.persistence.ProductsRepository;
import com.microcompany.productsservice.service.ProductsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductServiceController {
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceController.class);

    @Autowired
    private ProductsService servicioProds;

    @Autowired
    private ProductsRepository productsRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity getAllProducts() {
        List<Product> products = productsRepository.findAll();
        if (products != null && products.size() > 0) return ResponseEntity.status(HttpStatus.OK.value()).body(products);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(new StatusMessage(HttpStatus.NOT_FOUND.value(), "No se han encontrado productos"));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity getAProduct(@PathVariable("id") Long pid) {
        Product prod = productsRepository.findById(pid).orElse(null);
        if (prod != null) return ResponseEntity.status(HttpStatus.OK.value()).body(prod);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(new StatusMessage(HttpStatus.NOT_FOUND.value(), "No se han encontrado producto con id:" + pid));
    }

//    @RequestMapping(value = "", method = RequestMethod.POST)
    @PostMapping(value = "")
    public ResponseEntity createProduct(@RequestBody Product aProd) {
        productsRepository.save(aProd);
        if (aProd != null && aProd.getId() > 0) return ResponseEntity.status(HttpStatus.CREATED.value()).body(aProd);
        else
            return new ResponseEntity<>(new StatusMessage(HttpStatus.BAD_REQUEST.value(), "No se ha podido crear el producto. Revisa la petición."), HttpStatus.BAD_REQUEST);
    }



}