package com.microcompany.productsservice.controller;

import com.microcompany.productsservice.model.Product;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/default")
public interface IProductServiceController {
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAllProducts(@RequestParam(value = "nombrewith", defaultValue = "") String filtro);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity getAProduct(@PathVariable("id") Long pid);

    //    @RequestMapping(value = "", method = RequestMethod.POST)
    @PostMapping(value = "", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity createProduct(@RequestBody Product aProd);

    @PutMapping(value = "/{pid}")
    ResponseEntity updateProduct(@PathVariable Long pid, @RequestBody Product aProd);

    @DeleteMapping(value = "/{pid}")
    ResponseEntity deleteProduct(@PathVariable Long pid);

    @PostMapping(value = "/{pid}/clone")
    ResponseEntity cloneProduct(@PathVariable Long pid);
}
