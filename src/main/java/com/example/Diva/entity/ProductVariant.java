package com.example.Diva.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class ProductVariant extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Color color;

    @ManyToOne
    private Size size;
    @PositiveOrZero
    private Double price;
    @Min(0)
    private Integer stockQuantity;
    private String sku;

    @OneToMany(mappedBy = "variant", cascade = CascadeType.ALL)
    private List<ProductImage> images;
}
