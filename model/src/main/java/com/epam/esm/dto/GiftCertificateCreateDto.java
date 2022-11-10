package com.epam.esm.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class GiftCertificateCreateDto extends GenericCrudDto {

    @NotBlank(message = "Gift certificate name should be valid")
    String name;

    @NotBlank(message = "Gift certificate description should be valid")
    String description;

    @DecimalMin(value = "0.0", message = "Enter correct price")
    BigDecimal price;

    @Min(value = 1, message = "Gift certificate duration should be more or equals to 1")
    Integer duration;

    // fixme find solution or ask from mentor about it
    List<TagCreateDto> tagCreateDtoList;

}
