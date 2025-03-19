package com.microcompany.productsservice.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.microcompany.productsservice.exception.GlobalProductException;
import lombok.*;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "No debe ser vacio")
    @Size(min = 3, max = 20, message = "Debe tener entre 3 y 20 chars")
    private String name;

    @NotBlank
    @Pattern(regexp = "[1-9]{3}-[1-9]{3}-[1-9]{4}")
    private String serial;

}