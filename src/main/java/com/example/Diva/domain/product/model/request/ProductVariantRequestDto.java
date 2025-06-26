package com.example.Diva.domain.product.model.request;

import lombok.*;

import java.util.Collection;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductVariantRequestDto {
    private String sku;
    private Double price;
    private Integer stockQuantity;
    private Long colorId;
    private Long sizeId;
    private List<String> imageUrls;

}
