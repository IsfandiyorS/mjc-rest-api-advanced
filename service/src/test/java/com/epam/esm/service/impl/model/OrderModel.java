package com.epam.esm.service.impl.model;

import com.epam.esm.criteria.OrderCriteria;
import com.epam.esm.domain.Order;
import com.epam.esm.dto.certificate.OrderCreateDto;
import com.epam.esm.dto.certificate.OrderDto;
import com.epam.esm.dto.certificate.OrderUpdateDto;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

import static com.epam.esm.service.impl.model.GiftCertificateModel.ENTITY;
import static com.epam.esm.service.impl.model.GiftCertificateModel.GIFT_CERTIFICATE_DTO;
import static com.epam.esm.service.impl.model.UserModel.UPDATED_USER_DTO;
import static com.epam.esm.service.impl.model.UserModel.USER_ENTITY;

public final class OrderModel {

    public static final Long ORDER_ID = 1L;
    public static final PageRequest PAGE_REQUEST = PageRequest.of(0, 5);

    public static final Order ORDER_ENTITY = new Order(1L, BigDecimal.valueOf(10.1), 1L, USER_ENTITY, ENTITY);
    public static final Order CREATE_ORDER_ENTITY = new Order(4L, BigDecimal.valueOf(40.1), 4L, USER_ENTITY, ENTITY);
    public static final Order UPDATED_ORDER_ENTITY = new Order(1L, BigDecimal.valueOf(20.2), 1L, USER_ENTITY, ENTITY);

    public static final OrderDto ORDER_DTO = new OrderDto(1L, BigDecimal.valueOf(10.1), 1L, UPDATED_USER_DTO, GIFT_CERTIFICATE_DTO, "2022-11-19T06:12:15.156");
    public static final OrderCreateDto ORDER_CREATE_DTO = new OrderCreateDto(1L, 1L, 1L);
    public static final OrderUpdateDto ORDER_UPDATE_DTO = new OrderUpdateDto(1L, 2L);
    public static final OrderDto UPDATED_ORDER_DTO = new OrderDto(1L, BigDecimal.valueOf(20.2), 2L, UPDATED_USER_DTO, GIFT_CERTIFICATE_DTO, "2022-11-19T06:12:15.156");
    public static final OrderCriteria ORDER_CRITERIA = new OrderCriteria("user1", null, null, "asc");
}
