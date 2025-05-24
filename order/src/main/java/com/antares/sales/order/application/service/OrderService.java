package com.antares.sales.order.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.antares.sales.order.domain.model.order.Order;
import com.antares.sales.order.domain.repository.OrderRepository;
import com.antares.sales.order.interfaces.dto.OrderRequestDTO;
import com.antares.sales.order.interfaces.dto.OrderResponseDTO;
import com.antares.sales.order.shared.exception.NotFoundException;
import com.antares.sales.order.shared.mapper.OrderMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO requestDTO) {
        Order order = OrderMapper.toEntity(requestDTO);
        return OrderMapper.toDTO(orderRepository.save(order));
    }

    public OrderResponseDTO getOrder(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido no encontrado con ID: " + id));
        return OrderMapper.toDTO(order);
    }

    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper::toDTO)
                .toList();
    }

    @Transactional
    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO requestDTO) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido no encontrado con ID: " + id));

        existing.setCustomerCode(requestDTO.getCustomerCode());
        existing.setCustomerName(requestDTO.getCustomerName());
        existing.setOrderDate(requestDTO.getOrderDate());

        // Se reemplazan los ítems previos por los nuevos
        existing.getItems().clear();
        existing.getItems().addAll(OrderMapper.toEntity(requestDTO).getItems());
        existing.getItems().forEach(item -> item.setOrder(existing));

        return OrderMapper.toDTO(orderRepository.save(existing));
    }

    @Transactional
    public void deleteOrder(Long id) {
        Order existing = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido no encontrado con ID: " + id));
        orderRepository.delete(existing);
    }
}