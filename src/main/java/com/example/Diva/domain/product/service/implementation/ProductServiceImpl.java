package com.example.Diva.domain.product.service.implementation;

import com.example.Diva.domain.product.model.mapper.ProductMapper;
import com.example.Diva.domain.product.model.mapper.ProductVariantMapper;
import com.example.Diva.domain.product.model.request.ProductRequestDto;
import com.example.Diva.domain.product.model.request.ProductVariantRequestDto;
import com.example.Diva.domain.product.model.response.ProductResponseDto;
import com.example.Diva.domain.product.model.response.ProductVariantResponseDto;
import com.example.Diva.domain.product.service.contractor.ProductService;
import com.example.Diva.entity.*;
import com.example.Diva.repository.*;
import com.example.Diva.utill.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.Diva.domain.product.model.mapper.ProductMapper.toResponse;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SubCategoryRepository subCategoryRepository;
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public ResponseEntity<Object> createProduct(ProductRequestDto dto) {
        if(!subCategoryRepository.existsById(dto.getCategoryId())) {
            return new ResponseEntity<>(new BaseResponse<>(false, "Category not found", null), HttpStatus.NOT_FOUND);
        }
        SubCategory subCategory = subCategoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        if (!brandRepository.existsById(dto.getBrandId())) {
            return new ResponseEntity<>(new BaseResponse<>(false, "Brand not found", null), HttpStatus.NOT_FOUND);
        }
        Brand brand = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        Product product = ProductMapper.toEntity(dto, subCategory, brand);

        // Build variants
        List<ProductVariant> variants = new ArrayList<>();
        for (ProductVariantRequestDto variantDto : dto.getVariants()) {

            Color color = colorRepository.findById(variantDto.getColorId())
                    .orElseThrow(() -> new RuntimeException("Color not found"));

            Size size = sizeRepository.findById(variantDto.getSizeId())
                    .orElseThrow(() -> new RuntimeException("Size not found"));

            ProductVariant variant = ProductVariantMapper.toEntity(variantDto, product, color, size);

            // Set back-reference for each image
            variant.getImages().forEach(image -> image.setVariant(variant));

            variants.add(variant);
        }
        product.setVariants(variants);

        productRepository.save(product); // cascade will save variants + images

        ProductResponseDto response= toResponse(product);
        return new ResponseEntity<>(new BaseResponse<>(true, "Product created successfully", response), HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Object> getAllProducts() {
        if( productRepository.count() == 0) {
            return new ResponseEntity<>(new BaseResponse<>(false, "No products found",null), HttpStatus.NOT_FOUND);
        }
        List<Product> products = productRepository.findAll();
        List<ProductResponseDto> responseDtos = new ArrayList<>();
        for (Product product : products) {
            ProductResponseDto responseDto = toResponse(product);
            responseDtos.add(responseDto);
        }
        return new ResponseEntity<>(new BaseResponse<>(true, "Products retrieved successfully", responseDtos), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getProductById(Long id) {
        if (!productRepository.existsById(id)) {
            return new ResponseEntity<>(new BaseResponse<>(false, "Product not found", null), HttpStatus.NOT_FOUND);
        }
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        ProductResponseDto responseDto = toResponse(product);
        return new ResponseEntity<>(new BaseResponse<>(true, "Product retrieved successfully", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateProduct(Long id, ProductRequestDto dto) {
        if (!productRepository.existsById(id)) {
            return new ResponseEntity<>(new BaseResponse<>(false, "Product not found", null), HttpStatus.NOT_FOUND);
        }
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        SubCategory subCategory = subCategoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Brand brand = brandRepository.findById(dto.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        Product updatedProduct = ProductMapper.toEntity(dto, subCategory, brand);
        updatedProduct.setId(existingProduct.getId());

        List<ProductVariant> updatedVariants = new ArrayList<>();
        for (ProductVariantRequestDto variantDto : dto.getVariants()) {
            Color color = colorRepository.findById(variantDto.getColorId())
                    .orElseThrow(() -> new RuntimeException("Color not found"));

            Size size = sizeRepository.findById(variantDto.getSizeId())
                    .orElseThrow(() -> new RuntimeException("Size not found"));

            ProductVariant variant = ProductVariantMapper.toEntity(variantDto, updatedProduct, color, size);

            // Set back-reference for each image
            variant.getImages().forEach(image -> image.setVariant(variant));

            updatedVariants.add(variant);
        }
        updatedProduct.setVariants(updatedVariants);
        productRepository.save(updatedProduct); // cascade will save variants + images
        ProductResponseDto response = toResponse(updatedProduct);
        return new ResponseEntity<>(new BaseResponse<>(true, "Product updated successfully", response), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return new ResponseEntity<>(new BaseResponse<>(false, "Product not found", null), HttpStatus.NOT_FOUND);
        }
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepository.delete(product);

        return new ResponseEntity<>(new BaseResponse<>(true, "Product Deleted successfully", null), HttpStatus.OK);
    }
}
