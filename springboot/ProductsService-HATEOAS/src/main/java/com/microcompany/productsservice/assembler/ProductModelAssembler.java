package com.microcompany.productsservice.assembler;

import com.microcompany.productsservice.controller.ProductServiceController;
import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.resource.ProductResource;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProductModelAssembler extends RepresentationModelAssemblerSupport<Product, ProductResource> {
    public ProductModelAssembler(){
        super(ProductServiceController.class, ProductResource.class);
    }

    public ProductResource toModel(Product entity){
        ProductResource productModel = instantiateModel(entity);

        productModel = productModel.builder().
                id(entity.getId()).
                nombre(entity.getName()).
                numserie(entity.getSerial()).
                build();

        productModel.add(linkTo(
            methodOn(ProductServiceController.class).getAProduct(entity.getId())
        ).withSelfRel());

        productModel.add(linkTo(
            methodOn(ProductServiceController.class).getAllProducts("")
        ).withRel("productlist"));

        return productModel;
    }

}
