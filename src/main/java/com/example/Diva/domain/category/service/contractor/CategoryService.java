package com.example.Diva.domain.category.service.contractor;

import com.example.Diva.domain.category.model.request.CategoryRequestDto;
import com.example.Diva.domain.category.model.request.SubCategoryRequestDto;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    ResponseEntity<Object> createCategory(CategoryRequestDto categoryRequestDto);
    ResponseEntity<Object> addSubCategory(SubCategoryRequestDto subCategoryRequestDto);
    ResponseEntity<Object> getAllCategories();
    ResponseEntity<Object>  getSubcategoriesByCategoryId(Long categoryId);
}
