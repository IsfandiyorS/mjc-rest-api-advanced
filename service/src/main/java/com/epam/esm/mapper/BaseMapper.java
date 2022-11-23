package com.epam.esm.mapper;

import com.epam.esm.domain.Auditable;
import com.epam.esm.dto.GenericCrudDto;
import com.epam.esm.dto.GenericDto;
import org.mapstruct.MappingTarget;

import java.util.List;

public interface BaseMapper<E extends Auditable, C, UP extends GenericCrudDto, D extends GenericDto> {

    D toDto(E entity);

    List<D> toDtoList(List<E> entityList);

    E fromCreateDto(C createDto);

    E fromUpdateDto(UP updateDto, @MappingTarget E entity);

}
