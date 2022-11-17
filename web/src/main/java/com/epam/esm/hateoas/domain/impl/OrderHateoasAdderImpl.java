package com.epam.esm.hateoas.domain.impl;

import com.epam.esm.controller.OrderController;
import com.epam.esm.dto.certificate.OrderDto;
import com.epam.esm.hateoas.domain.GiftCertificateHateoasAdder;
import com.epam.esm.hateoas.domain.OrderHateoasAdder;
import com.epam.esm.hateoas.domain.UserHateoasAdder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.epam.esm.hateoas.MethodConst.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderHateoasAdderImpl implements OrderHateoasAdder {

    private static final Class<OrderController> CONTROLLER_CLASS = OrderController.class;
    private final UserHateoasAdder userHateoasAdder;
    private final GiftCertificateHateoasAdder giftCertificateHateoasAdder;

    @Autowired
    public OrderHateoasAdderImpl(UserHateoasAdder userHateoasAdder, GiftCertificateHateoasAdder giftCertificateHateoasAdder) {
        this.userHateoasAdder = userHateoasAdder;
        this.giftCertificateHateoasAdder = giftCertificateHateoasAdder;
    }

    @Override
    public void addLink(OrderDto orderDto) {
        orderDto.add(linkTo(methodOn(CONTROLLER_CLASS).getById(orderDto.getId())).withSelfRel());
        orderDto.add(linkTo(methodOn(CONTROLLER_CLASS).getAll(PAGE_NUMBER, PAGE_SIZE)).withRel(GET_ALL_METHOD));
        userHateoasAdder.addLink(orderDto.getUserDto());
        giftCertificateHateoasAdder.addLink(orderDto.getGiftCertificateDto());
    }

    @Override
    public void addLink(List<OrderDto> orderDtoList) {
        orderDtoList.forEach(this::addLink);
    }
}
