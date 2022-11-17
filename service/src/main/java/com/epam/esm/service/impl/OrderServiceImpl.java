package com.epam.esm.service.impl;

import com.epam.esm.criteria.OrderCriteria;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Order;
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

import static com.epam.esm.constant.UserColumn.ID;
import static com.epam.esm.enums.ErrorCodes.*;
import static java.lang.String.format;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final GiftCertificateRepository giftCertificateRepository;

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
    public Long create(OrderCreateDto createEntity) {

        Optional<User> optionalUser = userRepository.findById(createEntity.getUserId());
        if (optionalUser.isEmpty()){
            throw new ObjectNotFoundException(format(USER_NOT_FOUND.message, createEntity.getUserId()));
        }

        Optional<GiftCertificate> optionalGiftCertificate = giftCertificateRepository.findById(createEntity.getGiftCertificateId());
        if (optionalGiftCertificate.isEmpty()){
            throw new ObjectNotFoundException(format(OBJECT_NOT_FOUND.message, createEntity.getGiftCertificateId()));
        }

        Order order = orderMapper.fromCreateDto(createEntity);
        order.setUser(optionalUser.get());
        order.setGiftCertificate(optionalGiftCertificate.get());
        order.setPrice(optionalGiftCertificate.get().getPrice().multiply(BigDecimal.valueOf(createEntity.getOrderQuantity())));

        return orderRepository.save(order);
    }

    @Override
    public int delete(Long orderId) {

        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        validate(optionalOrder);

        Long delete = orderRepository.delete(optionalOrder.get());
        return Objects.equals(delete, optionalOrder.get().getId()) ?1:0;
    }


    // fixme change price of order depend on its quantity
    @Override
    public void update(OrderUpdateDto updateEntity) {
        Optional<Order> optionalOrder = orderRepository.findById(updateEntity.getId());
        validate(optionalOrder);

        Order order = orderMapper.fromUpdateDto(updateEntity, optionalOrder.get());
        BigDecimal price = order.getGiftCertificate().getPrice();
        long orderDifference = updateEntity.getOrderQuantity() - optionalOrder.get().getOrderQuantity();
        order.setPrice(order.getPrice().add(price.multiply(BigDecimal.valueOf(orderDifference))));

        orderRepository.update(order);
    }

    @Override
    public List<OrderDto> getOrderByUserId(PageRequest pageRequest, Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new ObjectNotFoundException(format(USER_NOT_FOUND.message, ID));
        }

        return orderMapper.toDtoList(orderRepository.findByUserId(pageRequest, userId));
    }

    @Override
    public OrderDto getOrderByIdAndUserId(Long orderId, Long userId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        validate(optionalOrder);
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()){
            throw new ObjectNotFoundException(format(USER_NOT_FOUND.message, "id"));
        }

        Optional<Order> order = orderRepository.findOrderByIdAndUserId(orderId, userId);
        if (order.isPresent()){
            return orderMapper.toDto(order.get());
        } else {
            throw new ObjectNotFoundException(format(OBJECT_NOT_FOUND.message, "Order"));
        }
    }

    // fixme to if I try to search by username;
    @Override
    public List<OrderDto> doFilter(OrderCriteria criteria, PageRequest pageable) {
        List<Order> orderList = orderRepository.find(criteria, pageable);
        return orderMapper.toDtoList(orderList);
    }

    @Override
    public void validate(Optional<Order> entity) {
        if (entity.isEmpty()){
            throw new ObjectNotFoundException(format(OBJECT_NOT_FOUND_ID.message, "Order"));
        }
    }

}
