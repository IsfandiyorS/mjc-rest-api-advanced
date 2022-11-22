package com.epam.esm.hateoas.impl;

import com.epam.esm.controller.TagController;
import com.epam.esm.dto.certificate.TagDto;
import com.epam.esm.hateoas.BaseHateoasAdder;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.epam.esm.hateoas.MethodConst.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TagHateoasAdderImpl implements BaseHateoasAdder<TagDto> {

    private static final Class<TagController> CONTROLLER_CLASS = TagController.class;

    @Override
    public void addLink(TagDto tagDto) {
        tagDto.add(linkTo(methodOn(CONTROLLER_CLASS).getTagById(tagDto.getId())).withSelfRel());
        tagDto.add(linkTo(methodOn(CONTROLLER_CLASS).getAll(PAGE_NUMBER, PAGE_SIZE)).withRel(GET_ALL_METHOD));
        tagDto.add(linkTo(methodOn(CONTROLLER_CLASS).deleteById(tagDto.getId())).withRel(DELETE_BY_ID_METHOD));
    }

    @Override
    public void addLink(List<TagDto> tagDtoList) {
        tagDtoList.forEach(this::addLink);
    }
}
