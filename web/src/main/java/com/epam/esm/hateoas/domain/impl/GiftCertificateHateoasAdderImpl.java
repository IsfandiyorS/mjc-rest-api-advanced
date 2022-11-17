package com.epam.esm.hateoas.domain.impl;

import com.epam.esm.controller.GiftCertificateController;
import com.epam.esm.dto.certificate.GiftCertificateDto;
import com.epam.esm.hateoas.domain.GiftCertificateHateoasAdder;
import com.epam.esm.hateoas.domain.TagHateoasAdder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.epam.esm.hateoas.MethodConst.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GiftCertificateHateoasAdderImpl implements GiftCertificateHateoasAdder {

    private static final Class<GiftCertificateController> CERTIFICATE_CONTROLLER_CLASS = GiftCertificateController.class;
    private final TagHateoasAdder tagHateoasAdder;

    @Autowired
    public GiftCertificateHateoasAdderImpl(TagHateoasAdder tagHateoasAdder) {
        this.tagHateoasAdder = tagHateoasAdder;
    }

    @Override
    public void addLink(GiftCertificateDto giftCertificateDto) {
        giftCertificateDto.add(linkTo(methodOn(CERTIFICATE_CONTROLLER_CLASS).getById(giftCertificateDto.getId())).withSelfRel());
        giftCertificateDto.add(linkTo(methodOn(CERTIFICATE_CONTROLLER_CLASS).getAll(PAGE_NUMBER, PAGE_SIZE)).withRel(GET_ALL_METHOD));
        giftCertificateDto.add(linkTo(methodOn(CERTIFICATE_CONTROLLER_CLASS).deleteById(giftCertificateDto.getId())).withRel(DELETE_BY_ID_METHOD));
        tagHateoasAdder.addLink(giftCertificateDto.getTagDtoList());
    }

    @Override
    public void addLink(List<GiftCertificateDto> giftCertificateDtoList) {
        giftCertificateDtoList.forEach(this::addLink);
    }
}
