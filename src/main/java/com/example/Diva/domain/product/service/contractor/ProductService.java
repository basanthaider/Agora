package com.example.Diva.domain.product.service.contractor;

import com.example.Diva.domain.product.model.request.ProductRequestDto;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<Object> createProduct(ProductRequestDto dto);
    ResponseEntity<Object> getAllProducts();
    ResponseEntity<Object> getProductById(Long id);
}
