package com.microcompany.productsservice.service;

import com.microcompany.productsservice.exception.ProductNotfoundException;
import com.microcompany.productsservice.model.Product;
import com.microcompany.productsservice.persistence.ProductsRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

//@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class ProductsServicePureTest {

    @InjectMocks
    ProductsService prodService;

    @Mock
    ProductsRepository productsRepositoryMock;

    @BeforeEach
    public void setUp() {

        List<Product> productsFake = List.of(
                new Product(1l, "Fake prod 1", "111-222-3333"),
                new Product(2l, "Fake prod 2", "111-222-4444"),
                new Product(3l, "Fake prod 3", "111-222-555")
        );
        Mockito.when(productsRepositoryMock.findByNameContaining("Fake")).thenReturn(productsFake);
        Mockito.when(productsRepositoryMock.findByNameContaining("a")).thenReturn(null);

    }

    @Test
    void givenProductsWhenSearchByTextThenIsNotNull() {
        List<Product> products = prodService.getProductsByText("Fake");
        assertThat(products).isNotNull();
        assertThat(products.size()).isGreaterThan(0);
    }

    @Test
    void givenProductsWhenSearchByTextNoExistThenException() {
        assertThatExceptionOfType(ProductNotfoundException.class).isThrownBy(() -> {
            List<Product> products = prodService.getProductsByText("a");
        });

    }

    @Test
    void givenValidProduct_WhenCreate_ThenThenIsNotNull() {
    }


}
