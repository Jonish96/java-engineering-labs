package com.paryogsaala.javaengineeringlabs.ordermanagement;


import com.paryogsaala.javaengineeringlabs.ordermanagement.OrderRepository.OrderRepository;
import com.paryogsaala.javaengineeringlabs.ordermanagement.exception.OrderNotFoundException;
import com.paryogsaala.javaengineeringlabs.ordermanagement.model.Order;
import com.paryogsaala.javaengineeringlabs.ordermanagement.service.OrderService;

import java.util.Optional;
import java.math.BigDecimal;
import java.util.UUID;
import java.util.List;

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
            orderService.createOrder(
                    "John",
                    "MacBook Pro",
                    1,
                    new BigDecimal("2499.99")
            );

            orderService.createOrder(
                    "Mary",
                    "AirPods",
                    2,
                    new BigDecimal("249.99")
            );

            System.out.println("Created: " + createdOrder);

            Order foundOrder =
                    orderService.getOrderById(createdOrder.getOrderId());

            List<Order> orderList = orderService.getAllOrders();

            System.out.println("Found: " + foundOrder);
            System.out.println("All Order List");
            for(Order order : orderList){
                System.out.println(order);
            }
//            System.out.println("Before cancellation: " + createdOrder);
//
//            Order cancelledOrder =
//                    orderService.cancelOrder(createdOrder.getOrderId());
//            orderService.cancelOrder(createdOrder.getOrderId());
            Order confirmedOrder =
                    orderService.confirmOrder(createdOrder.getOrderId());

            System.out.println("After confirmation: " + confirmedOrder);
            System.out.println("Before processing: " + confirmedOrder);

            Order processedOrder =
                    orderService.processOrder(createdOrder.getOrderId());

            System.out.println("After processing: " + processedOrder);

            Order shippedOrder =
                    orderService.shipOrder(createdOrder.getOrderId());
            System.out.println("After shipping: " + shippedOrder);
            System.out.println("Trying to cancel shipped order...");

            orderService.cancelOrder(shippedOrder.getOrderId());

        } catch (IllegalArgumentException e) {

            System.out.println("Order creation failed: " + e.getMessage());

        } catch (OrderNotFoundException e) {

            System.out.println("Order lookup failed: " + e.getMessage());
        }
        catch (IllegalStateException e) {
            System.out.println("Order operation failed: " + e.getMessage());
        }


    }
}