package com.fitmeal.repository;

import com.fitmeal.model.Cart;
import com.fitmeal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(User user);
}
