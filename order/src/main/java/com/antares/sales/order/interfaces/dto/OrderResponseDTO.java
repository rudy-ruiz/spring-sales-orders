package com.antares.sales.order.interfaces.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private String customerCode;
    private String customerName;
    private LocalDate orderDate;
    private List<OrderItemDTO> items;
}
