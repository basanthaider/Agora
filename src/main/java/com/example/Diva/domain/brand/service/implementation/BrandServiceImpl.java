package com.example.Diva.domain.brand.service.implementation;

import com.example.Diva.domain.brand.model.mapper.BrandMapper;
import com.example.Diva.domain.brand.model.request.BrandRequestDto;
import com.example.Diva.domain.brand.model.response.BrandResponseDto;
import com.example.Diva.domain.brand.service.contractor.BrandService;
import com.example.Diva.entity.Brand;
import com.example.Diva.repository.BrandRepository;
import com.example.Diva.utill.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;

    @Override
    public ResponseEntity<Object> createBrand(BrandRequestDto brandRequestDto) {
        Brand brand = BrandMapper.toEntity(brandRequestDto);
        brandRepository.save(brand);
        BrandResponseDto responseDto = BrandMapper.toResponse(brand);
        return new ResponseEntity<>(new BaseResponse<>(true, "Brand created successfully", responseDto), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> getAllBrands() {
        if (brandRepository.findAll().isEmpty()) {
            return new ResponseEntity<>(new BaseResponse<>(false, "No brands found", null), HttpStatus.NOT_FOUND);
        }
        List<BrandResponseDto> brandResponseDtos = brandRepository.findAll()
                .stream()
                .map(BrandMapper::toResponse)
                .toList();
        return new ResponseEntity<>(new BaseResponse<>(true, "Brand retrieved successfully", brandResponseDtos), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<Object> getBrandById(Long id) {
        if (!brandRepository.existsById(id)) {
            return new ResponseEntity<>(new BaseResponse<>(false, "Brand not found", null), HttpStatus.NOT_FOUND);
        }
        Brand brand = brandRepository.findById(id).get();
        BrandResponseDto responseDto = BrandMapper.toResponse(brand);
        return new ResponseEntity<>(new BaseResponse<>(true, "Brand retrieved successfully", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> updateBrand(Long id, BrandRequestDto brandRequestDto) {
        if (!brandRepository.existsById(id)) {
            return new ResponseEntity<>(new BaseResponse<>(false, "Brand not found", null), HttpStatus.NOT_FOUND);
        }
        Brand brand = brandRepository.findById(id).get();
        if (brandRequestDto.getName() != null) {
            brand.setName(brandRequestDto.getName());
        }
        brandRepository.save(brand);
        BrandResponseDto responseDto = BrandMapper.toResponse(brand);
        return new ResponseEntity<>(new BaseResponse<>(true, "Brand updated successfully", responseDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> deleteBrand(Long id) {
        if (!brandRepository.existsById(id)) {
            return new ResponseEntity<>(new BaseResponse<>(false, "Brand not found", null), HttpStatus.NOT_FOUND);
        }
        brandRepository.deleteById(id);
        return new ResponseEntity<>(new BaseResponse<>(true, "Brand deleted successfully", null), HttpStatus.OK);
    }
}
