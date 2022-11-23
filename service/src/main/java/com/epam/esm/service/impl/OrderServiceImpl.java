package com.epam.esm.service.impl;

import com.epam.esm.criteria.OrderCriteria;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Order;
import com.epam.esm.domain.Tag;
import com.epam.esm.domain.User;
import com.epam.esm.dto.certificate.OrderCreateDto;
import com.epam.esm.dto.certificate.OrderDto;
import com.epam.esm.dto.certificate.OrderUpdateDto;
import com.epam.esm.exceptions.ObjectNotFoundException;
import com.epam.esm.mapper.auth.OrderMapper;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.OrderRepository;
import com.epam.esm.repository.UserRepository;
import com.epam.esm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.epam.esm.constant.GiftCertificateColumn.GIFT_CERTIFICATE;
import static com.epam.esm.constant.UserColumn.ID;
import static com.epam.esm.constant.UserColumn.USER;
import static com.epam.esm.enums.ErrorCodes.*;
import static java.lang.String.format;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final GiftCertificateRepository giftCertificateRepository;
    private static final String ORDER_ENTITY = "Order";

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper, OrderRepository orderRepository, UserRepository userRepository, GiftCertificateRepository giftCertificateRepository) {
        this.orderMapper = orderMapper;
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.giftCertificateRepository = giftCertificateRepository;
    }

    @Override
    public OrderDto getById(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        validate(optionalOrder);
        return orderMapper.toDto(optionalOrder.get());
    }

    @Override
    public List<OrderDto> getAll(PageRequest pageRequest) {
        return orderMapper.toDtoList(orderRepository.findAll(pageRequest));
    }

    @Override
    public OrderDto create(OrderCreateDto createEntity) {

        Optional<User> optionalUser = userRepository.findById(createEntity.getUserId());
        if (optionalUser.isEmpty()) {
            throw new ObjectNotFoundException(format(OBJECT_NOT_FOUND_ID.message, USER, createEntity.getUserId()));
        }

        Optional<GiftCertificate> optionalGiftCertificate = giftCertificateRepository.findById(createEntity.getGiftCertificateId());
        if (optionalGiftCertificate.isEmpty()) {
            throw new ObjectNotFoundException(format(OBJECT_NOT_FOUND_ID.message, GIFT_CERTIFICATE, createEntity.getGiftCertificateId()));
        }

        Order order = orderMapper.fromCreateDto(createEntity);
        order.setUser(optionalUser.get());
        order.setGiftCertificate(optionalGiftCertificate.get());
        order.setPrice(optionalGiftCertificate.get().getPrice().multiply(BigDecimal.valueOf(createEntity.getOrderQuantity())));

        return orderMapper.toDto(orderRepository.save(order));
    }

    @Override
    public int delete(Long orderId) {

        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        validate(optionalOrder);

        Long deleteState = orderRepository.delete(optionalOrder.get());
        return deleteState == 2 ? 1 : 0;
    }

    @Override
    public OrderDto update(OrderUpdateDto updateEntity) {
        Optional<Order> optionalOrder = orderRepository.findById(updateEntity.getId());
        validate(optionalOrder);

        Order entityOrder = optionalOrder.get();
        BigDecimal price = entityOrder.getGiftCertificate().getPrice();
        long orderDifference = updateEntity.getOrderQuantity() - optionalOrder.get().getOrderQuantity();
        BigDecimal multiply = price.multiply(BigDecimal.valueOf(orderDifference));
        entityOrder.setPrice(entityOrder.getPrice().add(multiply));

        Order updatedOrder = orderRepository.update(orderMapper.fromUpdateDto(updateEntity, entityOrder));
        return orderMapper.toDto(updatedOrder);
    }

    @Override
    public List<OrderDto> getOrderByUserId(PageRequest pageRequest, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new ObjectNotFoundException(format(OBJECT_NOT_FOUND_ID.message, ID));
        }

        return orderMapper.toDtoList(orderRepository.findByUserId(pageRequest, userId));
    }

    @Override
    public OrderDto getOrderByIdAndUserId(Long orderId, Long userId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        validate(optionalOrder);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new ObjectNotFoundException(format(OBJECT_NOT_FOUND_ID.message, USER, ID));
        }

        Optional<Order> order = orderRepository.findOrderByIdAndUserId(orderId, userId);
        validate(order);
        return orderMapper.toDto(order.get());
    }

    @Override
    public List<OrderDto> doFilter(OrderCriteria criteria, PageRequest pageable) {
        List<Order> orderList = orderRepository.find(criteria, pageable);
        return orderMapper.toDtoList(orderList);
    }

    @Override
    public void validate(Optional<Order> entity) {
        if (entity.isEmpty()) {
            throw new ObjectNotFoundException(format(OBJECT_NOT_FOUND_ID.message, ORDER_ENTITY));
        }
    }

}
