package com.fitmeal.controller;

import com.fitmeal.model.Cart;
import com.fitmeal.service.CartService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public Cart addToCart(@RequestParam Long userId,
                          @RequestParam Long productId,
                          @RequestParam int quantity) {
        return cartService.addToCart(userId, productId, quantity);
    }
}
