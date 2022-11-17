package com.epam.esm.hateoas.domain.impl;

import com.epam.esm.controller.UserController;
import com.epam.esm.dto.auth.UserDto;
import com.epam.esm.hateoas.domain.UserHateoasAdder;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.epam.esm.hateoas.MethodConst.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserHateoasAdderImpl implements UserHateoasAdder {

    private static final Class<UserController> CONTROLLER_CLASS = UserController.class;

    @Override
    public void addLink(UserDto userDto) {
        userDto.add(linkTo(methodOn(CONTROLLER_CLASS).getById(userDto.getId())).withSelfRel());
        userDto.add(linkTo(methodOn(CONTROLLER_CLASS).getAll(PAGE_NUMBER, PAGE_SIZE)).withRel(GET_ALL_METHOD));
        userDto.add(linkTo(methodOn(CONTROLLER_CLASS).deleteById(userDto.getId())).withRel(DELETE_BY_ID_METHOD));
    }

    @Override
    public void addLink(List<UserDto> domainDtoList) {
        domainDtoList.forEach(this::addLink);
    }
}
