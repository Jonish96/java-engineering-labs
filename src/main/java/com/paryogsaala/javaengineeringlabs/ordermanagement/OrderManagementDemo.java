package com.paryogsaala.javaengineeringlabs.ordermanagement;


import com.paryogsaala.javaengineeringlabs.ordermanagement.OrderRepository.OrderRepository;
import com.paryogsaala.javaengineeringlabs.ordermanagement.exception.OrderNotFoundException;
import com.paryogsaala.javaengineeringlabs.ordermanagement.model.Order;
import com.paryogsaala.javaengineeringlabs.ordermanagement.service.OrderService;

import java.util.Optional;
import java.math.BigDecimal;
import java.util.UUID;

public class OrderManagementDemo {
    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepository();
        OrderService orderService = new OrderService(orderRepository);

        try {

            Order createdOrder = orderService.createOrder(
                    "Shawn",
                    "Iphone Duo",
                    1,
                    new BigDecimal("1999.99")
            );

            System.out.println("Created: " + createdOrder);

            Order foundOrder =
                    orderService.getOrderById(createdOrder.getOrderId());

            System.out.println("Found: " + foundOrder);

        } catch (IllegalArgumentException e) {

            System.out.println("Order creation failed: " + e.getMessage());

        } catch (OrderNotFoundException e) {

            System.out.println("Order lookup failed: " + e.getMessage());
        }


    }
}