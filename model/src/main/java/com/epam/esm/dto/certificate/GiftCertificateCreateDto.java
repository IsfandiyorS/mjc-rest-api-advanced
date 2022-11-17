package com.epam.esm.dto.certificate;

import com.epam.esm.dto.GenericCrudDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
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

    @NotNull(message = "Gift certificate price cannot be null")
    @Positive(message = "Gift certificate price should be positive")
    BigDecimal price;


    @NotNull(message = "Gift certificate duration cannot be null")
    @Min(value = 1, message = "Gift certificate duration should be more or equals to 1")
    Integer duration;

    List<TagCreateDto> tagCreateDtoList;

}
