package com.antares.sales.order.interfaces.dto;

import lombok.*;

import java.math.BigDecimal;

import com.antares.sales.order.domain.model.order.OrderType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private Long id;
    private String itemCode;
    private String description;
    private OrderType type;
    private Integer quantity;
    private BigDecimal price;
}