package com.epam.esm.dto.certificate;

import com.epam.esm.dto.GenericCrudDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class GiftCertificateUpdateDto extends GenericCrudDto {

    @NotNull(message = "Gift Certificate id must be valid")
    Long id;

    String name;

    String description;

    @DecimalMin(value = "0.0", message = "Enter correct price")
    @Positive(message = "Price must be positive number")
    BigDecimal price;

    @Min(value = 1, message = "Gift certificate duration should be more or equals to 1")
    Integer duration;

    List<TagCreateDto> tagCreateDtoList;

}
