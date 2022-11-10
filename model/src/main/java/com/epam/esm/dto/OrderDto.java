package com.epam.esm.dto;

import com.epam.esm.dto.auth.UserDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class OrderDto extends GenericDto {

    BigDecimal price;

    Long orderQuantity;

    List<UserDto> userDtoList;

    List<GiftCertificateDto> giftCertificateDtoList;

    @Builder(builderMethodName = "orderBuilder")
    public OrderDto(Long id, BigDecimal price, Long orderQuantity, List<UserDto> userDtoList, List<GiftCertificateDto> giftCertificateDtoList) {
        super(id);
        this.price = price;
        this.orderQuantity = orderQuantity;
        this.userDtoList = userDtoList;
        this.giftCertificateDtoList = giftCertificateDtoList;
    }
}
