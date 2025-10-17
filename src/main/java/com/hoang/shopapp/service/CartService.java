package com.hoang.shopapp.service;

import com.hoang.shopapp.entity.Cart;
import com.hoang.shopapp.entity.User;
import com.hoang.shopapp.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;

    public Cart getOrCreateCart(User user) {
        Cart cart = cartRepository.findByUser(user);
        // Nếu user chưa có cart-> tạo mới cart
        if (cart == null) {
            Cart newCart = new Cart(user);
            return cartRepository.save(newCart);
        }
        // Nếu có rồi thì tìm cart để trả về
        return cart;
    }
}
