package com.paryogsaala.javaengineeringlabs.ordermanagement.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateOrderRequest {
    @NotBlank(message = "Customer Name is required.")
    private String customerName;
    @NotBlank(message = "Product Name is required.")
    private String productName;
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
    @DecimalMin(
            value = "0.01",
            message = "Price must be greater than 0"
    )
    private BigDecimal price;
}
