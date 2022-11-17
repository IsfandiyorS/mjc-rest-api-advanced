package com.epam.esm.repository;

import com.epam.esm.criteria.OrderCriteria;
import com.epam.esm.domain.Order;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends GenericCrudRepository<Order, OrderCriteria>{

    List<Order> findByUserId(PageRequest pageRequest, Long userId);

    Optional<Order> findOrderByIdAndUserId(Long orderId, Long userId);
}
