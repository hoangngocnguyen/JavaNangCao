package com.hoang.shopapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItemRequest {
    private int productId;
    private int quantity;
}
