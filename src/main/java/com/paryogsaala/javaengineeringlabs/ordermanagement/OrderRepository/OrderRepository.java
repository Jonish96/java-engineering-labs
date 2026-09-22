package com.paryogsaala.javaengineeringlabs.ordermanagement.OrderRepository;

import com.paryogsaala.javaengineeringlabs.ordermanagement.model.Order;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OrderRepository {
    private final Map<UUID, Order> ordersById = new HashMap<>();

    private void save(Order order) {
       ordersById.put(order.getOrderId(),order);

    }
    public Order findById(UUID orderId) {
        return ordersById.get(orderId);
    }
}
