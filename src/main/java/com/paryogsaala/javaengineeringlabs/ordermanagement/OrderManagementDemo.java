package com.paryogsaala.javaengineeringlabs.ordermanagement;


import com.paryogsaala.javaengineeringlabs.ordermanagement.enums.OrderStatus;
import com.paryogsaala.javaengineeringlabs.ordermanagement.model.Order;

import java.math.BigDecimal;

public class OrderManagementDemo {
    public static void main(String[] args) {
        try{
            Order order = new Order(
                    "Shawn",
                    "Iphone Duo",
                    1,
                    new BigDecimal("1999.99")
            ); System.out.println(order);
            System.out.println(order.getPrice());
        } catch (IllegalArgumentException e) {

            System.out.println("Order creation failed: " + e.getMessage());
        }

        System.out.println("Program continues");


    }
}