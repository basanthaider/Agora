package com.example.Diva.domain.product.service.implementation;


import com.example.Diva.domain.product.model.mapper.ProductVariantMapper;
import com.example.Diva.domain.product.model.request.ColorRequestDto;
import com.example.Diva.domain.product.model.request.SizeRequestDto;
import com.example.Diva.domain.product.model.response.ColorResponseDto;
import com.example.Diva.domain.product.model.response.SizeResponseDto;
import com.example.Diva.domain.product.service.contractor.ProductVariantsService;
import com.example.Diva.entity.Color;
import com.example.Diva.entity.Size;
import com.example.Diva.repository.ColorRepository;
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
    ColorRepository SizeRepository;
    @Autowired
    SizeRepository sizeRepository;

    @Override
    public ResponseEntity<Object> createColor(ColorRequestDto colorRequestDto) {
        Color color = ProductVariantMapper.toEntity(colorRequestDto);
        SizeRepository.save(color);
        ColorResponseDto response = ProductVariantMapper.toResponse(color);

        return new ResponseEntity<>(new BaseResponse<>(true, "Color created successfully", response), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> DeleteColor(Long id) {
        if (!SizeRepository.existsById(id)) {
            return new ResponseEntity<>(new BaseResponse<>(false, "Color not found", null), HttpStatus.NOT_FOUND);
        }
        SizeRepository.deleteById(id);
        return new ResponseEntity<>(new BaseResponse<>(true, "Color deleted successfully", null), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Object> getAllColors() {
        if (SizeRepository.findAll().isEmpty()) {
            return new ResponseEntity<>(new BaseResponse<>(false, "No colors found", null), HttpStatus.NOT_FOUND);
        }
        List<ColorResponseDto> ColorResponseDtos = SizeRepository.findAll()
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
        SizeRepository.deleteById(id);
        return new ResponseEntity<>(new BaseResponse<>(true, "Size deleted successfully", null), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> getAllSizes() {
        if (SizeRepository.findAll().isEmpty()) {
            return new ResponseEntity<>(new BaseResponse<>(false, "No colors found", null), HttpStatus.NOT_FOUND);
        }
        List<ColorResponseDto> sizeResponseDtos = SizeRepository.findAll()
                .stream()
                .map(ProductVariantMapper::toResponse)
                .toList();
        return new ResponseEntity<>(new BaseResponse<>(true, "Colors retrieved successfully", sizeResponseDtos), HttpStatus.OK);

    }
}
