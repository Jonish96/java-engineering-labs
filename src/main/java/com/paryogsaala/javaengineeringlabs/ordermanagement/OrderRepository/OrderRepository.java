package com.paryogsaala.javaengineeringlabs.ordermanagement.OrderRepository;

import com.paryogsaala.javaengineeringlabs.ordermanagement.model.Order;

import java.util.*;

public class OrderRepository {
    private final Map<UUID, Order> ordersById = new HashMap<>();

    public void save(Order order) {
       ordersById.put(order.getOrderId(),order);

    }
    public Optional<Order> findById(UUID orderId) {
        return Optional.ofNullable(ordersById.get(orderId));
    }
    public List<Order> findAll() {
        List<Order> orders = new ArrayList<>();
        return new ArrayList<>(ordersById.values());
    }
}
