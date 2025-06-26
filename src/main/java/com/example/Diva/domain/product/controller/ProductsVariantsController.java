package com.example.Diva.domain.product.controller;

import com.example.Diva.domain.product.model.request.ColorRequestDto;
import com.example.Diva.domain.product.model.request.SizeRequestDto;
import com.example.Diva.domain.product.service.contractor.ProductVariantsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/v1/variants")
public class ProductsVariantsController {
    @Autowired
    ProductVariantsService productVariantsService;

    @PostMapping("/colors")
    @PreAuthorize("hasAuthority('MERCHANT')or hasAuthority('ADMIN')")
    ResponseEntity<Object> createColor(@RequestBody ColorRequestDto colorRequestDto) {
        return ResponseEntity.ok(productVariantsService.createColor(colorRequestDto));
    }
    @GetMapping("/colors")
    @PreAuthorize("hasAuthority('MERCHANT')or hasAuthority('ADMIN')")
    ResponseEntity<Object> getAllColors() {
        return ResponseEntity.ok(productVariantsService.getAllColors());
    }
    @GetMapping("/colors/{id}")
    @PreAuthorize("hasAuthority('MERCHANT')or hasAuthority('ADMIN')")
    ResponseEntity<Object> DeleteColor(@PathVariable Long id) {
        return ResponseEntity.ok(productVariantsService.DeleteColor(id));
    }
    @PostMapping("/sizes")
    @PreAuthorize("hasAuthority('MERCHANT')or hasAuthority('ADMIN')")
    ResponseEntity<Object> createSize(@RequestBody SizeRequestDto sizeRequestDto) {
        return ResponseEntity.ok(productVariantsService.createSize(sizeRequestDto));
    }
    @GetMapping("/sizes")
    @PreAuthorize("hasAuthority('MERCHANT')or hasAuthority('ADMIN')")
    ResponseEntity<Object> getAllSizes() {
        return ResponseEntity.ok(productVariantsService.getAllSizes());
    }
    @GetMapping("/sizes/{id}")
    @PreAuthorize("hasAuthority('MERCHANT')or hasAuthority('ADMIN')")
    ResponseEntity<Object> DeleteSize(@PathVariable Long id) {
        return ResponseEntity.ok(productVariantsService.DeleteSize(id));
    }


}
