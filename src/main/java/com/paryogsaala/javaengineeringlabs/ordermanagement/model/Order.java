package com.paryogsaala.javaengineeringlabs.ordermanagement.model;

import com.paryogsaala.javaengineeringlabs.ordermanagement.enums.OrderStatus;

import java.util.UUID;
import java.math.BigDecimal;

public class Order {
    private UUID orderId;
    private String customerName;
    private String productName;
    private int quantity;
    private BigDecimal price;
    private OrderStatus status;
}
