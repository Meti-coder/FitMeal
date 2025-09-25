package com.fitmeal.service;

import com.fitmeal.model.*;
import com.fitmeal.repository.CartRepository;
import com.fitmeal.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
    }

    // Checkout: create order from user cart
    public Order placeOrder(Long userId) {
        Cart cart = cartRepository.findByUser(new User() {{
            setId(userId);
        }}).orElseThrow();

        Order order = new Order();
        order.setUser(cart.getUser());

        List<OrderItem> orderItems = new ArrayList<>();
        double total = 0;

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            double price = cartItem.getQuantity() * cartItem.getProduct().getPrice();
            orderItem.setPrice(price);
            total += price;
            orderItems.add(orderItem);
        }

        order.setItems(orderItems);
        order.setTotalPrice(total);
        order.setStatus("CONFIRMED");

        // Clear cart after placing order
        cart.getItems().clear();
        cartRepository.save(cart);

        return orderRepository.save(order);
    }

    public List<Order> getOrdersByUser(Long userId) {
        User user = new User();
        user.setId(userId);
        return orderRepository.findByUser(user);
    }
}
