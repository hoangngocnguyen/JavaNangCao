package com.hoang.shopapp.service;

import com.hoang.shopapp.dto.CartItemRequest;
import com.hoang.shopapp.dto.CartItemDto;
import com.hoang.shopapp.dto.CartResponse;
import com.hoang.shopapp.entity.Cart;
import com.hoang.shopapp.entity.CartItem;
import com.hoang.shopapp.entity.Product;
import com.hoang.shopapp.entity.User;
import com.hoang.shopapp.repository.CartItemRepository;
import com.hoang.shopapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class CartItemService {
    @Autowired
    CartItemRepository cartItemRepository;

    @Autowired
    CartService cartService;

    @Autowired
    ProductRepository productRepository;

    // Lấy cart_item từ cart
    public List<CartItemDto> getCartItems(Cart cart) {
        // Lấy cartItem ra
        List<CartItem> cartItems= cartItemRepository.findByCart(cart);

        return cartItems.stream().map(CartItemDto::new).toList();
    }


    // Thêm sản phẩm vào giỏ
    public CartResponse save(User user, CartItemRequest cartItemRequest) {
        Cart cart = cartService.getOrCreateCart(user);

        int productId = cartItemRequest.getProductId();
        int quantity = cartItemRequest.getQuantity();

        // Kiểm tra sản phẩm tổn tại
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        CartItem cartItem = cartItemRepository.findByCart_User_IdAndProduct_ProductId(user.getId(), productId);

        // Nếu sản phẩm chưa có, thêm vào
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
        } else {
            // Nếu sản phẩm đã có trong cart_items, cộng thêm số lượng
            int newQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(newQuantity);
        }

        // Lưu vào db
        cartItemRepository.save(cartItem);

        // Trả về DTO
        List<CartItem> cartItems = cartItemRepository.findByCart(cart);
        List<CartItemDto> items = cartItems.stream().map(item -> new CartItemDto(item)).toList();
        return new CartResponse(items);
    }


    // Cập nhật số lượng sản phẩm
    public void updateQuantity(UUID userId, int productId, int quantity) {
        // Lấy được cart item
        CartItem cartItem = cartItemRepository.findByCart_User_IdAndProduct_ProductId(userId, productId);

        if (cartItem != null) {
            // Nếu số lượng = 0 thì xóa
            if (quantity == 0) {
                delete(userId, productId);
                return;
            }

            cartItem.setQuantity(quantity);

            // Lưu lại vào db
            cartItemRepository.save(cartItem);
        }
    }

    // Xóa sản phẩm trong giỏ hàng
    public void delete(UUID userId, int productId) {
        cartItemRepository.deleteByCart_User_IdAndProduct_ProductId(userId, productId);
    }
}

