package com.epam.esm.dto;

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
public class GiftCertificateDto extends GenericDto{

    String name;

    String description;

    BigDecimal price;

    Integer duration;

    List<TagDto> tagDtoList;

    @Builder(builderMethodName = "giftCertificateBuilder")
    public GiftCertificateDto(Long id, String name, String description, BigDecimal price, Integer duration, List<TagDto> tagDtoList) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.tagDtoList = tagDtoList;
    }
}
