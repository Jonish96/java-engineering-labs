package com.paryogsaala.javaengineeringlabs.ordermanagement.service;

import com.paryogsaala.javaengineeringlabs.ordermanagement.OrderRepository.OrderRepository;
import com.paryogsaala.javaengineeringlabs.ordermanagement.exception.OrderNotFoundException;
import com.paryogsaala.javaengineeringlabs.ordermanagement.model.Order;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class OrderService {
    private final OrderRepository orderRepository;
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    public Order createOrder(
            String customerName,
            String productName,
            int quantity,
            BigDecimal price) {

        Order order = new Order(
                customerName,
                productName,
                quantity,
                price
        );

        orderRepository.save(order);

        return order;
    }
    public Order getOrderById(UUID orderId) {

        return orderRepository
                .findById(orderId)
                .orElseThrow(() ->
                        new OrderNotFoundException(
                                "Order not found with ID: " + orderId
                        )
                );
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
    public Order cancelOrder(UUID orderId) {
        Order order = getOrderById(orderId);
        order.cancel();
        return order;
    }
    public Order confirmOrder(UUID orderId) {
        Order order = getOrderById(orderId);
        order.confirm();
        return order;
    }
    public Order processOrder(UUID orderId) {
        Order order = getOrderById(orderId);
        order.process();
        return order;
    }
    public Order shipOrder(UUID orderId) {
        Order order = getOrderById(orderId);
        order.shipped();
        return order;
    }
}
