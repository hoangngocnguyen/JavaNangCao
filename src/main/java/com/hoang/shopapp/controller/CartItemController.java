package com.hoang.shopapp.controller;

import com.hoang.shopapp.dto.CartItemRequest;
import com.hoang.shopapp.dto.CartItemDto;
import com.hoang.shopapp.dto.CartResponse;
import com.hoang.shopapp.entity.Cart;
import com.hoang.shopapp.entity.User;
import com.hoang.shopapp.repository.ProductRepository;
import com.hoang.shopapp.repository.UserRepository;
import com.hoang.shopapp.service.CartItemService;
import com.hoang.shopapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping("/api/cart/items")
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartService cartService;

    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public List<CartItemDto> getCartItems(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartService.getOrCreateCart(user);
        return cartItemService.getCartItems(cart);
    }

    @PostMapping
    public ResponseEntity<CartResponse> add(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CartItemRequest cartItemRequest) {
        // Kiểm tra user có tồn tại
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));


        CartResponse result = cartItemService.save(user, cartItemRequest);

        return ResponseEntity.ok(result);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<?> updateQuantity(@AuthenticationPrincipal UserDetails userDetails,
                                            @PathVariable int productId,
                                            @RequestBody Map<String, Integer> body) {
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        int quantity = body.get("quantity");
        cartItemService.updateQuantity(user.getId(), productId, quantity);
        return ResponseEntity.ok("Cập nhật số lượng thành công");
    }

    @DeleteMapping("/cart-item/{productId}")
    public ResponseEntity<?> deleteItem(@AuthenticationPrincipal UserDetails userDetails,
                                        @PathVariable int productId) {

        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        cartItemService.delete(user.getId(), productId);
        return ResponseEntity.ok("Xóa cart item thành công");
    }

}
