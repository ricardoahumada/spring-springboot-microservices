package com.microcompany.productsservice.config;

import com.microcompany.productsservice.exception.ProductNotfoundException;
import com.microcompany.productsservice.model.StatusMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlocalExceptionController {

    @ExceptionHandler(value = ProductNotfoundException.class)
    public ResponseEntity handleProductNotFoundException(ProductNotfoundException ex) {
        return ResponseEntity.status(404).body(new StatusMessage(404, ex.getMessage()));
    }

}
