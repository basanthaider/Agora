package com.example.Diva.domain.product.controller;

import com.example.Diva.domain.product.model.request.ProductRequestDto;
import com.example.Diva.domain.product.service.contractor.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/getAll")
    ResponseEntity<Object> getAllProducts() {
        return productService.getAllProducts();
    }
    @GetMapping("/{id}")
    ResponseEntity<Object> getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('MERCHANT')")
    ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
        return productService.updateProduct(id, productRequestDto);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('MERCHANT')")
    ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }



}
