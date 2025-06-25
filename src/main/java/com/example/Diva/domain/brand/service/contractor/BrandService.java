package com.example.Diva.domain.brand.service.contractor;

import com.example.Diva.domain.brand.model.request.BrandRequestDto;
import com.example.Diva.domain.brand.model.response.BrandResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BrandService {
    ResponseEntity<Object> createBrand(BrandRequestDto brandRequestDto);
    ResponseEntity<Object> getAllBrands();
    ResponseEntity<Object> getBrandById(Long id);
    ResponseEntity<Object> updateBrand(Long id, BrandRequestDto brandRequestDto);
    ResponseEntity<Object> deleteBrand(Long id);
}
