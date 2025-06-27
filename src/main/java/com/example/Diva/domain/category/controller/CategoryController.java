package com.example.Diva.domain.category.controller;

import com.example.Diva.domain.category.model.request.CategoryRequestDto;
import com.example.Diva.domain.category.model.request.SubCategoryRequestDto;
import com.example.Diva.domain.category.service.contractor.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/api/v1/categories")
public class CategoryController {
    @Autowired
    private CategoryService service;
    @PostMapping
    @PreAuthorize("hasAuthority('MERCHANT')")
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequestDto dto) {
        return ResponseEntity.ok(service.createCategory(dto));
    }

    @PostMapping("/subcategories")
    @PreAuthorize("hasAuthority('MERCHANT')")
    public ResponseEntity<?> addSubCategory(@RequestBody SubCategoryRequestDto dto) {
        return ResponseEntity.ok(service.addSubCategory(dto));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {

        return ResponseEntity.ok(service.getAllCategories());
    }

    @GetMapping("/{id}/subcategories")
    public ResponseEntity<?> getSub(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSubcategoriesByCategoryId(id));
    }
}
