package com.epam.esm.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class GiftCertificateUpdateDto extends GenericCrudDto{

    String name;

    String description;

    BigDecimal price;

    Integer duration;

    List<TagUpdateDto> tagUpdateDtoList;

}
