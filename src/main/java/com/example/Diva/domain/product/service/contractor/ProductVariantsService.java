package com.example.Diva.domain.product.service.contractor;

import com.example.Diva.domain.product.model.request.ColorRequestDto;
import com.example.Diva.domain.product.model.request.SizeRequestDto;
import org.springframework.http.ResponseEntity;

public interface ProductVariantsService {
    ResponseEntity<Object> createColor(ColorRequestDto colorRequestDto);
    ResponseEntity<Object> DeleteColor(Long id);
    ResponseEntity<Object>getAllColors();

    ResponseEntity<Object>createSize(SizeRequestDto sizeRequestDto);
    ResponseEntity<Object> DeleteSize(Long id);
    ResponseEntity<Object>getAllSizes();
    ResponseEntity<Object> getVariantById(Long id);
    ResponseEntity<Object> getAllVariants();




}
