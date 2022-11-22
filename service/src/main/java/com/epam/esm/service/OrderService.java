package com.epam.esm.service;

import com.epam.esm.criteria.OrderCriteria;
import com.epam.esm.domain.Order;
import com.epam.esm.dto.certificate.OrderCreateDto;
import com.epam.esm.dto.certificate.OrderDto;
import com.epam.esm.dto.certificate.OrderUpdateDto;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface OrderService extends GenericCrudService<Order, OrderCreateDto, OrderUpdateDto, OrderDto, Long, OrderCriteria> {

    List<OrderDto> getOrderByUserId(PageRequest pageRequest, Long userId);

    OrderDto getOrderByIdAndUserId(Long orderId, Long userId);

}
