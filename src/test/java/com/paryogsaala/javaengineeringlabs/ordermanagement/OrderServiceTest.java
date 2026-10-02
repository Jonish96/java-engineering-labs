package com.paryogsaala.javaengineeringlabs.ordermanagement;

import com.paryogsaala.javaengineeringlabs.ordermanagement.OrderRepository.OrderRepository;
import com.paryogsaala.javaengineeringlabs.ordermanagement.exception.OrderNotFoundException;
import com.paryogsaala.javaengineeringlabs.ordermanagement.model.Order;
import com.paryogsaala.javaengineeringlabs.ordermanagement.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderServiceTest {
    private OrderService orderService;
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository = Mockito.mock(OrderRepository.class);
        orderService = new OrderService(orderRepository);
    }
    @Test
    void shouldReturnOrderWhenOrderExists() {

        UUID orderId = UUID.randomUUID();

        Order order = new Order(
                "Shawn",
                "iPhone",
                1,
                new BigDecimal("1299.99")
        );

        Mockito.when(orderRepository.findById(orderId))
                .thenReturn(Optional.of(order));

        Order result = orderService.getOrderById(orderId);

        assertEquals(order, result);
    }
    @Test
    void shouldThrowExceptionWhenOrderDoesNotExist() {
        UUID orderId = UUID.randomUUID();
        Mockito.when(orderRepository.findById(orderId))
                .thenReturn(Optional.empty());
        assertThrows(
                OrderNotFoundException.class,
                () -> orderService.getOrderById(orderId)
        );
    }
    @Test
    void shouldSaveOrderWhenCreatingOrder() {

        Order order = orderService.createOrder(
                "Shawn",
                "iPhone",
                1,
                new BigDecimal("1299.99")
        );

        Mockito.verify(orderRepository)
                .save(order);
    }
}
