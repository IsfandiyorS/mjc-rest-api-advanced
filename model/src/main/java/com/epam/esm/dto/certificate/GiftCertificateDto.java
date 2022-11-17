package com.epam.esm.dto.certificate;

import com.epam.esm.dto.GenericDto;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class GiftCertificateDto extends RepresentationModel<GiftCertificateDto> implements GenericDto {

    Long id;
    String name;

    String description;

    BigDecimal price;

    Integer duration;

    List<TagDto> tagDtoList;

    @Builder(builderMethodName = "giftCertificateBuilder")
    public GiftCertificateDto(Long id, String name, String description, BigDecimal price, Integer duration, List<TagDto> tagDtoList) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.tagDtoList = tagDtoList;
    }
}
