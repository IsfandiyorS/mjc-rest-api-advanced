package com.epam.esm.hateoas;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public interface BaseHateoasAdder<D extends RepresentationModel<D>> {

    void addLink(D domainDto);

    void addLink(List<D> domainList);
}
