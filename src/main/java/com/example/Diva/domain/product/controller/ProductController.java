package com.example.Diva.domain.product.controller;

import com.example.Diva.domain.product.model.request.ProductRequestDto;
import com.example.Diva.domain.product.service.contractor.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @PostMapping
    @PreAuthorize("hasAuthority('MERCHANT')")
    ResponseEntity<Object> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        return productService.createProduct(productRequestDto);
    }

}
