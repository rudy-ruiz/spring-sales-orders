package com.antares.sales.order.shared.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.antares.sales.order.domain.model.order.Order;
import com.antares.sales.order.domain.model.order.OrderItem;
import com.antares.sales.order.interfaces.dto.OrderItemDTO;
import com.antares.sales.order.interfaces.dto.OrderRequestDTO;
import com.antares.sales.order.interfaces.dto.OrderResponseDTO;

public class OrderMapper {

    public static Order toEntity(OrderRequestDTO dto) {
        Order order = Order.builder()
                .customerCode(dto.getCustomerCode())
                .customerName(dto.getCustomerName())
                .orderDate(dto.getOrderDate())
                .build();

        List<OrderItem> items = dto.getItems().stream()
                .map(item -> OrderItem.builder()
                        .itemCode(item.getItemCode())
                        .description(item.getDescription())
                        .type(item.getType())
                        .quantity(item.getQuantity())
                        .price(item.getPrice())
                        .order(order)
                        .build())
                .collect(Collectors.toList());

        order.setItems(items);
        return order;
    }

    public static OrderResponseDTO toDTO(Order entity) {
        return OrderResponseDTO.builder()
                .id(entity.getId())
                .customerCode(entity.getCustomerCode())
                .customerName(entity.getCustomerName())
                .orderDate(entity.getOrderDate())
                .items(entity.getItems().stream().map(OrderMapper::toDTO).collect(Collectors.toList()))
                .build();
    }

    public static OrderItemDTO toDTO(OrderItem item) {
        return OrderItemDTO.builder()
                .id(item.getId())
                .itemCode(item.getItemCode())
                .description(item.getDescription())
                .type(item.getType())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .build();
    }
}