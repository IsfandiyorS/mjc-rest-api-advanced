package com.epam.esm.dto.certificate;

import com.epam.esm.dto.GenericCrudDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class GiftCertificateUpdateDto implements GenericCrudDto {

    @NotNull(message = "Gift Certificate id must be valid")
    Long id;

    String name;

    String description;

    @Positive(message = "Gift certificate price should be positive")
    BigDecimal price;

    @Min(value = 1, message = "Gift certificate duration should be more or equals to 1")
    Integer duration;

    List<TagCreateDto> tagCreateDtoList;

}
