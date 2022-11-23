package com.epam.esm.service.impl;

import com.epam.esm.domain.Order;
import com.epam.esm.dto.certificate.OrderDto;
import com.epam.esm.enums.ErrorCodes;
import com.epam.esm.exceptions.ObjectNotFoundException;
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

import static com.epam.esm.constant.GiftCertificateColumn.*;
import static com.epam.esm.constant.GiftCertificateColumn.ID;
import static com.epam.esm.service.impl.domain.GiftCertificateModel.*;
import static com.epam.esm.service.impl.domain.OrderModel.*;
import static com.epam.esm.service.impl.domain.OrderModel.PAGE_REQUEST;
import static com.epam.esm.service.impl.domain.UserModel.USER_ENTITY;
import static com.epam.esm.service.impl.domain.UserModel.USER_ID;
import static java.lang.String.format;
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
    void testGetById() {
        when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(ORDER_ENTITY));

        OrderDto actualOrderDto = orderService.getById(ORDER_ID);

        assertNotNull(actualOrderDto);
        verify(orderRepository, times(1)).findById(ORDER_ID);
    }


    @Test
    void testGetByIdMethodThrowObjectNotFoundException() {
        when(orderRepository.findById(NOT_AVAILABLE_USER_ID)).thenReturn(Optional.empty());

        ObjectNotFoundException objectNotFoundException =
                assertThrows(ObjectNotFoundException.class, () -> orderService.getById(NOT_AVAILABLE_USER_ID));

        String expectedException = format(ErrorCodes.OBJECT_NOT_FOUND_ID.message, ORDER);
        String actualException = objectNotFoundException.getMessage();

        assertEquals(expectedException, actualException);
        verify(orderRepository, times(1)).findById(NOT_AVAILABLE_USER_ID);
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
    void testCreateMethodThrowAlreadyExistException() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(USER_ENTITY));
        when(giftCertificateRepository.findById(NOT_AVAILABLE_GIFT_CERTIFICATE_ID))
                .thenReturn(Optional.empty());

        ObjectNotFoundException objectNotFoundException = assertThrows(ObjectNotFoundException.class,
                () -> orderService.create(INCORRECT_ORDER_CREATE_DTO));

        String expectedException = format(ErrorCodes.OBJECT_NOT_FOUND_ID.message, GIFT_CERTIFICATE, ID);
        String actualException = objectNotFoundException.getMessage();

        assertEquals(expectedException, actualException);

        verify(giftCertificateRepository, times(1)).findById(INCORRECT_ORDER_CREATE_DTO.getGiftCertificateId());
    }
    @Test
    void getOrderByUserId() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(USER_ENTITY));
        when(orderRepository.findByUserId(PAGE_REQUEST, USER_ID)).thenReturn(List.of(ORDER_ENTITY));

        List<OrderDto> actualDto = orderService.getOrderByUserId(PAGE_REQUEST, USER_ID);

        assertNotNull(actualDto);
        verify(orderRepository, times(1)).findByUserId(PAGE_REQUEST, USER_ID);
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
    void getOrderByIdAndUserIdMethodThrow() {
        when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(ORDER_ENTITY));
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(USER_ENTITY));
        when(orderRepository.findOrderByIdAndUserId(ORDER_ID, USER_ID)).thenReturn(Optional.of(ORDER_ENTITY));

        OrderDto actualDto = orderService.getOrderByIdAndUserId(ORDER_ID, USER_ID);

        assertNotNull(actualDto);
        verify(orderRepository, times(1)).findOrderByIdAndUserId(ORDER_ID, USER_ID);
    }

    @Test
    void delete() {
        when(orderRepository.findById(ORDER_ID)).thenReturn(Optional.of(ORDER_ENTITY));
        when(orderRepository.delete(ORDER_ENTITY)).thenReturn(DELETED_STATE);

        int actualId = orderService.delete(ORDER_ID);
        assertEquals(1, actualId);
        verify(orderRepository, times(1)).delete(ORDER_ENTITY);
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
        String expectedException = format(ErrorCodes.OBJECT_NOT_FOUND_ID.message, ORDER);
        String actualException = objectNotFoundException.getMessage();
        assertEquals(expectedException, actualException);
    }
}