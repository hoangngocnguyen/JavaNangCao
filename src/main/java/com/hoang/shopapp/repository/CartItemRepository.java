package com.hoang.shopapp.repository;

import com.hoang.shopapp.entity.Cart;
import com.hoang.shopapp.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    // Lấy danh sách cart_items cho cart
    public List<CartItem> findByCart(Cart cart);

    public List<CartItem> findByCart_User_Id(UUID userId);


    // Thêm sản phẩm vào giỏ hàng
    // Tìm sản phẩm trong giỏ hàng của user
    public CartItem findByCart_User_IdAndProduct_ProductId(UUID userId, int productId);


    // Xóa cart item bằng userId và productId
    public void deleteByCart_User_IdAndProduct_ProductId(UUID userId, int productId);

}
