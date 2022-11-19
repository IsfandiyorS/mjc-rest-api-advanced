package com.epam.esm.mapper.auth;

import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.dto.certificate.GiftCertificateCreateDto;
import com.epam.esm.dto.certificate.GiftCertificateDto;
import com.epam.esm.dto.certificate.GiftCertificateUpdateDto;
import com.epam.esm.mapper.BaseMapper;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR, unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = TagMapper.class)
public interface GiftCertificateMapper extends BaseMapper<GiftCertificate, GiftCertificateCreateDto, GiftCertificateUpdateDto, GiftCertificateDto> {

    @Mapping(target = "tagDtoList", source = "tagList")
    @Override
    GiftCertificateDto toDto(GiftCertificate entity);


    @Mapping(source = "updateDto.name", target = "name", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "updateDto.description", target = "description", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "updateDto.price", target = "price", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "updateDto.duration", target = "duration", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Override
    GiftCertificate fromUpdateDto(GiftCertificateUpdateDto updateDto, @MappingTarget GiftCertificate entity);
}