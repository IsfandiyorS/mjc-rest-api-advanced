package com.epam.esm.hateoas;

import java.util.List;

public interface BaseHateoasAdder<D> {

    void addLink(D domainDto);

    void addLink(List<D> domainList);
}
