package com.epam.esm.service.impl;

import com.epam.esm.domain.Order;
import com.epam.esm.dto.certificate.OrderDto;
import com.epam.esm.handler.ObjectNotFoundException;
import com.epam.esm.mapper.auth.*;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.OrderRepository;
import com.epam.esm.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.epam.esm.service.impl.model.GiftCertificateModel.CERTIFICATE_ID;
import static com.epam.esm.service.impl.model.GiftCertificateModel.ENTITY;
import static com.epam.esm.service.impl.model.OrderModel.*;
import static com.epam.esm.service.impl.model.UserModel.USER_ENTITY;
import static com.epam.esm.service.impl.model.UserModel.USER_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private GiftCertificateRepository giftCertificateRepository;

    @Spy
    private TagMapper tagMapper = Mappers.getMapper(TagMapper.class);
    @Spy
    private GiftCertificateMapper giftCertificateMapper = new GiftCertificateMapperImpl(tagMapper);

    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Spy
    private OrderMapper orderMapper = new OrderMapperImpl(userMapper, giftCertificateMapper);

    @Test
    void getById() {
        when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(ORDER_ENTITY));

        OrderDto actualOrderDto = orderService.getById(ORDER_ID);

        assertNotNull(actualOrderDto);
        verify(orderRepository, times(1)).findById(ORDER_ID);
    }

    @Test
    void update() {
        when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(ORDER_ENTITY));
        when(orderRepository.update(ORDER_ENTITY)).thenReturn(UPDATED_ORDER_ENTITY);

        OrderDto actualDto = orderService.update(ORDER_UPDATE_DTO);

        assertEquals(UPDATED_ORDER_DTO.getPrice(), actualDto.getPrice());
        verify(orderRepository, times(1)).update(ORDER_ENTITY);
    }

    @Test
    void getAll() {
        when(orderRepository.findAll(PAGE_REQUEST)).thenReturn(anyList());

        List<OrderDto> actualDto = orderService.getAll(PAGE_REQUEST);

        assertNotNull(actualDto);
        verify(orderRepository, times(1)).findAll(PAGE_REQUEST);
    }

    @Test
    void create() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(USER_ENTITY));
        when(giftCertificateRepository.findById(CERTIFICATE_ID)).thenReturn(Optional.of(ENTITY));
        when(orderRepository.save(any(Order.class))).thenReturn(CREATE_ORDER_ENTITY);

        OrderDto orderDto = orderService.create(ORDER_CREATE_DTO);

        assertEquals(CREATE_ORDER_ENTITY.getId(), orderDto.getId());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void delete() {
        when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(ORDER_ENTITY));
        when(orderRepository.delete(ORDER_ENTITY)).thenReturn(ORDER_ID);

        int actualId = orderService.delete(ORDER_ID);
        assertEquals(1, actualId);
        verify(orderRepository, times(1)).delete(ORDER_ENTITY);
    }

    @Test
    void getOrderByUserId() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(USER_ENTITY));
        when(orderRepository.findByUserId(PAGE_REQUEST,USER_ID)).thenReturn(List.of(ORDER_ENTITY));

        List<OrderDto> actualDto = orderService.getOrderByUserId(PAGE_REQUEST,USER_ID);

        assertNotNull(actualDto);
        verify(orderRepository, times(1)).findByUserId(PAGE_REQUEST,USER_ID);
    }

    @Test
    void getOrderByIdAndUserId() {
        when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(ORDER_ENTITY));
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(USER_ENTITY));
        when(orderRepository.findOrderByIdAndUserId(ORDER_ID, USER_ID)).thenReturn(Optional.of(ORDER_ENTITY));

        OrderDto actualDto = orderService.getOrderByIdAndUserId(ORDER_ID, USER_ID);

        assertNotNull(actualDto);
        verify(orderRepository, times(1)).findOrderByIdAndUserId(ORDER_ID, USER_ID);
    }

    @Test
    void doFilter() {
        when(orderRepository.find(ORDER_CRITERIA, PAGE_REQUEST)).thenReturn(List.of(ORDER_ENTITY));

        List<OrderDto> actualDto = orderService.doFilter(ORDER_CRITERIA, PAGE_REQUEST);

        assertNotNull(actualDto);
        verify(orderRepository, times(1)).find(ORDER_CRITERIA, PAGE_REQUEST);
    }

    @Test
    void validate() {
        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class, ()
                -> orderService.validate(Optional.empty()));
        String expectedException = "Order is not found with provided id";
        String actualException = objectNotFoundException.getMessage();
        assertEquals(expectedException, actualException);
    }
}