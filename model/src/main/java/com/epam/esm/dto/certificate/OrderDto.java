package com.epam.esm.dto.certificate;

import com.epam.esm.dto.GenericDto;
import com.epam.esm.dto.auth.UserDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Builder
@AllArgsConstructor
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

}
