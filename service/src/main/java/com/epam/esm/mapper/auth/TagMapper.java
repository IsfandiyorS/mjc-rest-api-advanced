package com.epam.esm.mapper.auth;

import com.epam.esm.domain.Tag;
import com.epam.esm.dto.certificate.TagCreateDto;
import com.epam.esm.dto.certificate.TagDto;
import com.epam.esm.dto.certificate.TagUpdateDto;
import com.epam.esm.mapper.BaseMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper extends BaseMapper<Tag, TagCreateDto, TagUpdateDto, TagDto> {

    @Mapping(source = "updateDto.name", target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Override
    Tag fromUpdateDto(TagUpdateDto updateDto, Tag entity);
}
