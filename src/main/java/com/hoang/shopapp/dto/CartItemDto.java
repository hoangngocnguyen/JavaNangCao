package com.hoang.shopapp.dto;

import com.hoang.shopapp.entity.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class CartItemDto {
    private int productId;
    private String productName;
    private BigDecimal price;
    private int discountPercent;
    private BigDecimal salePrice;
    private String imageSrc;
    private int quantity;

    public CartItemDto (CartItem cartItem) {
        this.productId = cartItem.getProduct().getProductId();
        this.productName = cartItem.getProduct().getProductName();
        this.price = cartItem.getProduct().getPrice();
        this.discountPercent = cartItem.getProduct().getDiscountPercent();
        this.salePrice = cartItem.getProduct().getSalePrice();
        this.imageSrc = cartItem.getProduct().getImageSrc();
        this.quantity = cartItem.getQuantity();
    }
}
