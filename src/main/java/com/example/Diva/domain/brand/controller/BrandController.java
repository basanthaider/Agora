package com.example.Diva.domain.brand.controller;

import com.example.Diva.domain.brand.model.request.BrandRequestDto;
import com.example.Diva.domain.brand.service.contractor.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brands")
public class BrandController {
    @Autowired
    private BrandService brandService;

     @PostMapping()
     @PreAuthorize("hasAuthority('MERCHANT')")
     public ResponseEntity<Object> createBrand(@RequestBody BrandRequestDto brandRequestDto) {
         return ResponseEntity.ok(brandService.createBrand(brandRequestDto));
     }
     @GetMapping()
     public ResponseEntity<Object> getAllBrands() {
         return ResponseEntity.ok(brandService.getAllBrands());
     }
     @GetMapping("/{id}")
     public ResponseEntity<Object> getBrandById(@PathVariable Long id) {
         return ResponseEntity.ok(brandService.getBrandById(id));
     }
     @PutMapping("/{id}")
     @PreAuthorize("hasAuthority('MERCHANT')")
     public ResponseEntity<Object> updateBrand(@PathVariable Long id, @RequestBody BrandRequestDto brandRequestDto) {
         return ResponseEntity.ok(brandService.updateBrand(id, brandRequestDto));
     }
     @DeleteMapping("/{id}")
     @PreAuthorize("hasAuthority('MERCHANT')")
     public ResponseEntity<Object> deleteBrand(@PathVariable Long id) {
         return ResponseEntity.ok( brandService.deleteBrand(id));
     }
}
