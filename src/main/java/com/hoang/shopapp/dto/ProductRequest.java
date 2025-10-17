package com.hoang.shopapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ProductRequest {
    private String productName;
    private BigDecimal price;
    private int discountPercent;
    private int quantity;
    private String brand;
    private String origin;
    private String imageSrc;
    private String description;
    private int categoryId;
}
