package com.paryogsaala.javaengineeringlabs.ordermanagement;


import com.paryogsaala.javaengineeringlabs.ordermanagement.OrderRepository.OrderRepository;
import com.paryogsaala.javaengineeringlabs.ordermanagement.model.Order;
import java.util.Optional;
import java.math.BigDecimal;
import java.util.UUID;

public class OrderManagementDemo {
    public static void main(String[] args) {
        OrderRepository orderRepository = new OrderRepository();

        try{
            Order order = new Order(
                    "Shawn",
                    "Iphone Duo",
                    1,
                    new BigDecimal("1999.99")
            );
            orderRepository.save(order);
            UUID orderId = UUID.randomUUID();
            Optional<Order> foundOrder =
                    orderRepository.findById(order.getOrderId());
          foundOrder.ifPresent(found->{
              System.out.println("found: " + found);
          });
          Optional<Order> notFoundId = orderRepository.findById(orderId);
            notFoundId.ifPresentOrElse(
                    found -> System.out.println("Order found: " + found),
                    () -> System.out.println("Order not found")
            );

        } catch (IllegalArgumentException e) {

            System.out.println("Order creation failed: " + e.getMessage());
        }


    }
}