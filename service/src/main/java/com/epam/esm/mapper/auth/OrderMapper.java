package com.epam.esm.mapper.auth;

import com.epam.esm.domain.Order;
import com.epam.esm.dto.certificate.OrderCreateDto;
import com.epam.esm.dto.certificate.OrderDto;
import com.epam.esm.dto.certificate.OrderUpdateDto;
import com.epam.esm.mapper.BaseMapper;
import org.mapstruct.*;

import java.util.List;

import static org.mapstruct.NullValuePropertyMappingStrategy.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UserMapper.class, GiftCertificateMapper.class})
public interface OrderMapper extends BaseMapper<Order, OrderCreateDto, OrderUpdateDto, OrderDto> {

    @Mapping(source = "user", target = "userDto")
    @Mapping(source = "giftCertificate", target = "giftCertificateDto")
    @Mapping(source = "createDate", target = "orderedTime", dateFormat = "yyyy-MM-dd HH:mm")
    @Override
    OrderDto toDto(Order entity);

    @Mapping(source = "user", target = "userDto")
    @Mapping(source = "giftCertificate", target = "giftCertificateDto")
    @Mapping(source = "createDate", target = "orderedTime", dateFormat = "yyyy-MM-dd HH:mm")
    @Override
    List<OrderDto> toDtoList(List<Order> entityList);

    @Mapping(source = "updateDto.orderQuantity", target = "orderQuantity", nullValuePropertyMappingStrategy = IGNORE)
    @Override
    Order fromUpdateDto(OrderUpdateDto updateDto, @MappingTarget Order entity);
}
