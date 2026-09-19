package com.paryogsaala.javaengineeringlabs.ordermanagement.model;

import com.paryogsaala.javaengineeringlabs.ordermanagement.enums.OrderStatus;

import java.util.UUID;
import java.math.BigDecimal;

public class Order {
    //data fields
    private UUID orderId;
    private String customerName;
    private String productName;
    private int quantity;
    private BigDecimal price;
    private OrderStatus status;

    //constructor
    public Order(String customerName, String productName, int quantity, BigDecimal price) {

        validateCustomerName(customerName);
        validateProductName(productName);
        validaeQuantity(quantity);
        validPrice(price);

        this.orderId = UUID.randomUUID();
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.status = OrderStatus.CREATED;
    }
    private void validateCustomerName(String customerName) {
        if (customerName == null || customerName.isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty");
        }
    }
    private void validateProductName(String productName) {
        if (productName == null || productName.isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
    }
    private void validaeQuantity(int quantity) {
        if(quantity<=0){
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
    }
    private void validPrice(BigDecimal price) {
        if(price==null || price.compareTo(BigDecimal.ZERO)<=0){
            throw new IllegalArgumentException("Price must be greater than 0");
        }
    }
    private OrderStatus getOrderStatus() {
        return status;
    }


    @Override
    public String toString() {
        return orderId + " " + customerName + " " + productName + " " + quantity + " " + price.toString() + " " + status;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderStatus getStatus() {
        return status;
    }
}
