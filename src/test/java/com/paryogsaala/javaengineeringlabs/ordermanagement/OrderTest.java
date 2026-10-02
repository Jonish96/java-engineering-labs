package com.paryogsaala.javaengineeringlabs.ordermanagement;
import com.paryogsaala.javaengineeringlabs.ordermanagement.enums.OrderStatus;
import com.paryogsaala.javaengineeringlabs.ordermanagement.model.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class OrderTest {
    @Test
    void shouldCreateOrderWithCreatedStatus() {

        Order order = new Order(
                "Shawn",
                "iPhone",
                1,
                new BigDecimal("1299.99")
        );

        assertEquals(
                OrderStatus.CREATED,
                order.getStatus()
        );
    }
    @Test
    void shouldNotShipOrderBeforeProcessing() {

        Order order = new Order(
                "Shawn",
                "iPhone",
                1,
                new BigDecimal("1299.99")
        );

        assertThrows(
                IllegalStateException.class,
                order::shipped
        );
    }
    @Test
    void shouldShipOrderAfterProcessing() {

        Order order = new Order(
                "Shawn",
                "iPhone",
                1,
                new BigDecimal("1299.99")
        );

        order.confirm();
        order.process();
        order.shipped();

        assertEquals(
                OrderStatus.SHIPPED,
                order.getStatus()
        );
    }
    @Test
    void shouldNotCancelShippedOrder() {

        Order order = new Order(
                "Shawn",
                "iPhone",
                1,
                new BigDecimal("1299.99")
        );

        order.confirm();
        order.process();
        order.shipped();

        assertThrows(
                IllegalStateException.class,
                order::cancel
        );
    }
}
