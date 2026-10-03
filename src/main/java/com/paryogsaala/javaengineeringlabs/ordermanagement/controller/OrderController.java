package com.paryogsaala.javaengineeringlabs.ordermanagement.controller;

import com.paryogsaala.javaengineeringlabs.ordermanagement.dto.CreateOrderRequest;
import com.paryogsaala.javaengineeringlabs.ordermanagement.service.OrderService;
import com.paryogsaala.javaengineeringlabs.ordermanagement.model.Order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public Order createOrder(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        return  orderService.createOrder(
                createOrderRequest.getCustomerName(),
                createOrderRequest.getProductName(),
                createOrderRequest.getQuantity(),
                createOrderRequest.getPrice()
        );
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable UUID id) {
        return orderService.getOrderById(id);
    }
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    @PutMapping("/{id}/confirm")
    public Order confirmOrder(@PathVariable UUID id) {
        return orderService.confirmOrder(id);
    }
    @PutMapping("/{id}/process")
    public Order processOrder(@PathVariable UUID id) {
        return orderService.processOrder(id);
    }
    @PutMapping("/{id}/shipped")
    public Order shipOrder(@PathVariable UUID id) {
        return orderService.shipOrder(id);
    }
    @PutMapping("/{id}/cancel")
    public Order cancelOrder(@PathVariable UUID id) {
        return orderService.cancelOrder(id);
    }

}
