package com.hoang.shopapp.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table (name = "products")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "discount_percent")
    private int discountPercent;

    @Column(name = "sale_price", insertable = false, updatable = false)  // cot salePrice tự generate o mySQL
    private BigDecimal salePrice;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "rating")
    private BigDecimal rating;

    @Column(name = "sold")
    private int sold;

    @Column(name = "brand")
    private String brand;

    @Column(name = "origin")
    private String origin;

    @Column(name = "image_src")
    private String imageSrc;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
