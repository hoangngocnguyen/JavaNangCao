package com.hoang.shopapp.repository;

import com.hoang.shopapp.entity.Cart;
import com.hoang.shopapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    public Cart findByUser(User user);
}
