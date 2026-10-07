package com.paryogsaala.javaengineeringlabs.ordermanagement.controller;
import com.paryogsaala.javaengineeringlabs.ordermanagement.exception.OrderNotFoundException;
import com.paryogsaala.javaengineeringlabs.ordermanagement.model.Order;
import com.paryogsaala.javaengineeringlabs.ordermanagement.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import java.math.BigDecimal;
import java.util.UUID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.mockito.Mockito;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockitoBean
    private OrderService orderService;


    @Test
    void shouldReturnBadRequestWhenQuantityIsInvalid() throws Exception {
        String requestBody = """
        {
          "customerName": "Shawn",
          "productName": "iPhone",
          "quantity": -1,
          "price": 1299.99
        }
        """;


        mockMvc.perform(
                post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andExpect(status().isBadRequest()
        ).andExpect(jsonPath("$.message.quantity").value("Quantity must be at least 1"));

    }

    @Test
    void shouldCreateOrderWhenRequestIsValid() throws Exception {
        String requestBody = """
        {
          "customerName": "Shawn",
          "productName": "iPhone",
          "quantity": 1,
          "price": 1299.99
        }
        """;

        Order order = new Order(
                "Shawn",
                "iPhone",
                1,
                new BigDecimal("1299.99")
        );
        Mockito.when(
                orderService.createOrder(
                        "Shawn",
                        "iPhone",
                        1,
                        new BigDecimal("1299.99")
                )
        ).thenReturn(order);
        mockMvc.perform(
                        post("/api/orders")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerName").value("Shawn"))
                .andExpect(jsonPath("$.productName").value("iPhone"))
                .andExpect(jsonPath("$.quantity").value(1))
                .andExpect(jsonPath("$.price").value(1299.99))
                .andExpect(jsonPath("$.status").value("CREATED"));
        Mockito.verify(orderService).createOrder(
                "Shawn",
                "iPhone",
                1,
                new BigDecimal("1299.99")
        );
    }
    @Test
    void shouldReturnNotFoundWhenOrderDoesNotExist() throws Exception {
        UUID orderId = UUID.randomUUID();
        Mockito.when(orderService.getOrderById(orderId))
                .thenThrow(
                        new OrderNotFoundException(
                                "Order not found with ID: " + orderId
                        )
                );
        mockMvc.perform(
                        get("/api/orders/{id}", orderId)
                )
                .andExpect(status().isNotFound())
                .andExpect(
                        jsonPath("$.message")
                                .value("Order not found with ID: " + orderId)
                );
    }
}
