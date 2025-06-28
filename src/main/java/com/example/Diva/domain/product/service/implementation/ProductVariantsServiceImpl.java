package com.example.Diva.domain.product.service.implementation;


import com.example.Diva.domain.product.model.mapper.ProductVariantMapper;
import com.example.Diva.domain.product.model.request.ColorRequestDto;
import com.example.Diva.domain.product.model.request.SizeRequestDto;
import com.example.Diva.domain.product.model.response.ColorResponseDto;
import com.example.Diva.domain.product.model.response.ProductVariantResponseDto;
import com.example.Diva.domain.product.model.response.SizeResponseDto;
import com.example.Diva.domain.product.service.contractor.ProductVariantsService;
import com.example.Diva.entity.Color;
import com.example.Diva.entity.ProductVariant;
import com.example.Diva.entity.Size;
import com.example.Diva.repository.ColorRepository;
import com.example.Diva.repository.ProductVariantRepository;
import com.example.Diva.repository.SizeRepository;
import com.example.Diva.utill.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductVariantsServiceImpl implements ProductVariantsService {
    @Autowired
    ColorRepository colorRepository;
    @Autowired
    SizeRepository sizeRepository;
    @Autowired
    ProductVariantRepository productVariantRepository;

    @Override
    public ResponseEntity<Object> createColor(ColorRequestDto colorRequestDto) {
        Color color = ProductVariantMapper.toEntity(colorRequestDto);
        colorRepository.save(color);
        ColorResponseDto response = ProductVariantMapper.toResponse(color);

        return new ResponseEntity<>(new BaseResponse<>(true, "Color created successfully", response), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> DeleteColor(Long id) {
        if (!colorRepository.existsById(id)) {
            return new ResponseEntity<>(new BaseResponse<>(false, "Color not found", null), HttpStatus.NOT_FOUND);
        }
        colorRepository.deleteById(id);
        return new ResponseEntity<>(new BaseResponse<>(true, "Color deleted successfully", null), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Object> getAllColors() {
        if (colorRepository.findAll().isEmpty()) {
            return new ResponseEntity<>(new BaseResponse<>(false, "No colors found", null), HttpStatus.NOT_FOUND);
        }
        List<ColorResponseDto> ColorResponseDtos = colorRepository.findAll()
                .stream()
                .map(ProductVariantMapper::toResponse)
                .toList();
        return new ResponseEntity<>(new BaseResponse<>(true, "Colors retrieved successfully", ColorResponseDtos), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Object> createSize(SizeRequestDto sizeRequestDto) {
        Size size = ProductVariantMapper.toEntity(sizeRequestDto);
        sizeRepository.save(size);
        SizeResponseDto response = ProductVariantMapper.toResponse(size);

        return new ResponseEntity<>(new BaseResponse<>(true, "Size created successfully", response), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> DeleteSize(Long id) {
        if (!sizeRepository.existsById(id)) {
            return new ResponseEntity<>(new BaseResponse<>(false, "Size not found", null), HttpStatus.NOT_FOUND);
        }
        colorRepository.deleteById(id);
        return new ResponseEntity<>(new BaseResponse<>(true, "Size deleted successfully", null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllSizes() {
        if (colorRepository.findAll().isEmpty()) {
            return new ResponseEntity<>(new BaseResponse<>(false, "No colors found", null), HttpStatus.NOT_FOUND);
        }
        List<ColorResponseDto> sizeResponseDtos = colorRepository.findAll()
                .stream()
                .map(ProductVariantMapper::toResponse)
                .toList();
        return new ResponseEntity<>(new BaseResponse<>(true, "Colors retrieved successfully", sizeResponseDtos), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Object> getVariantById(Long id) {
        if (!productVariantRepository.existsById(id)) {
            return new ResponseEntity<>(new BaseResponse<>(false, "Variant not found", null), HttpStatus.NOT_FOUND);
        }
        ProductVariant variant = productVariantRepository.findById(id).get();
        ProductVariantResponseDto response = ProductVariantMapper.toResponse(variant);
        return new ResponseEntity<>(new BaseResponse<>(true, "Variant retrieved successfully", response), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllVariants() {
        if (!productVariantRepository.findAll().isEmpty()) {
            return new ResponseEntity<>(new BaseResponse<>(false, "Variants not found", null), HttpStatus.NOT_FOUND);
        }
        List<ProductVariantResponseDto> variantResponseDtos = productVariantRepository.findAll()
                .stream()
                .map(ProductVariantMapper::toResponse)
                .toList();
        return new ResponseEntity<>(new BaseResponse<>(true, "Variants retrieved successfully", variantResponseDtos), HttpStatus.OK);

    }
}
