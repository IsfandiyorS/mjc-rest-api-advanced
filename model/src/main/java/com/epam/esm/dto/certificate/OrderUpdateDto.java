package com.epam.esm.dto.certificate;

import com.epam.esm.dto.GenericCrudDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class OrderUpdateDto extends GenericCrudDto {

    @NotNull(message = "Order quantity cannot be null")
    @Min(value = 1, message = "Order quantity should be more or equal to 0")
    Long id;

    @Min(value = 1, message = "Order quantity should be more or equal to 1")
    Long orderQuantity;

}
