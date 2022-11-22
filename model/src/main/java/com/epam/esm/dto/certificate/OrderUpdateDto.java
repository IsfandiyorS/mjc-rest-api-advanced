package com.epam.esm.dto.certificate;

import com.epam.esm.dto.GenericCrudDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class OrderUpdateDto implements GenericCrudDto {

    @NotNull(message = "Order quantity cannot be null")
    @Min(value = 1, message = "Order quantity should be more or equal to 0")
    Long id;

    @Min(value = 1, message = "Order quantity should be more or equal to 1")
    Long orderQuantity;

}
