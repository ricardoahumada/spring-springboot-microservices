package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.model.Product;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/default")
@Validated
public interface IProductServiceController {
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllProducts(@Size(min = 3, max = 10) @RequestParam(value = "nombrewith", defaultValue = "") String filtro);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAProduct(@Min(1) @PathVariable("id") Long pid);

    //    @RequestMapping(value = "", method = RequestMethod.POST)
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity createProduct(@Valid @RequestBody Product aProd);

    @PutMapping(value = "/{pid}")
    ResponseEntity updateProduct(@PathVariable Long pid, @RequestBody Product aProd);

    @DeleteMapping(value = "/{pid}")
    ResponseEntity deleteProduct(@PathVariable Long pid);

    @PostMapping(value = "/{pid}/clone")
    ResponseEntity cloneProduct(@PathVariable Long pid);
}
