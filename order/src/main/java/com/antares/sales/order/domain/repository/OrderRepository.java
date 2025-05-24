package com.antares.sales.order.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.antares.sales.order.domain.model.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {}
