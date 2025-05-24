package com.antares.sales.order.interfaces.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    @NotBlank
    private String customerCode;

    @NotBlank
    private String customerName;

    @NotNull
    private LocalDate orderDate;

    @NotEmpty
    private List<OrderItemDTO> items;
}