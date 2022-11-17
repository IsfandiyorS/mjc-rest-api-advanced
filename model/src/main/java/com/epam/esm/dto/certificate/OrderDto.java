package com.epam.esm.dto.certificate;

import com.epam.esm.dto.GenericDto;
import com.epam.esm.dto.auth.UserDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class OrderDto extends RepresentationModel<OrderDto> implements GenericDto {

    Long id;
    BigDecimal price;

    Long orderQuantity;

    String orderedTime;

    UserDto userDto;

    GiftCertificateDto giftCertificateDto;


    @Builder(builderMethodName = "orderBuilder")
    public OrderDto(Long id, BigDecimal price, Long orderQuantity, UserDto userDto,
                    GiftCertificateDto giftCertificateDto, String orderedTime) {
        this.id = id;
        this.price = price;
        this.orderQuantity = orderQuantity;
        this.userDto = userDto;
        this.giftCertificateDto = giftCertificateDto;
        this.orderedTime = orderedTime;
    }
}
