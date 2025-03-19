package com.microcompany.productsservice.config;

import com.microcompany.productsservice.exception.GlobalProductException;
import com.microcompany.productsservice.exception.NewProductException;
import com.microcompany.productsservice.exception.ProductNotfoundException;
import com.microcompany.productsservice.model.StatusMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlocalExceptionController {

    @ExceptionHandler(value = GlobalProductException.class)
    public ResponseEntity handleGlobalProductException(GlobalProductException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new StatusMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(value = ProductNotfoundException.class)
    public ResponseEntity handleProductNotFoundException(ProductNotfoundException ex) {
        return ResponseEntity.status(404).body(new StatusMessage(404, ex.getMessage()));
    }

    /*@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(value = NewProductException.class)
    public String handleNewProductException(NewProductException ex) {
        return ex.getMessage();
    }*/

}
