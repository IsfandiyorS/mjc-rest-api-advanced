package com.epam.esm.dto.certificate;

import com.epam.esm.dto.GenericDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class GiftCertificateDto extends RepresentationModel<GiftCertificateDto> implements GenericDto {

    Long id;

    String name;

    String description;

    BigDecimal price;

    Integer duration;

    List<TagDto> tagDtoList;
}
