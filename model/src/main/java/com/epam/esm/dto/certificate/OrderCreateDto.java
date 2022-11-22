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
public class OrderCreateDto implements GenericCrudDto {

    @NotNull(message = "Order quantity cannot be null")
    @Min(value = 1, message = "Order quantity should be more or equal to 0")
    Long orderQuantity;

    @NotNull(message = "User id quantity cannot be null")
    @Min(value = 1, message = "User id should be correct")
    Long userId;

    @NotNull(message = "Gift Certificate id quantity cannot be null")
    @Min(value = 1, message = "Gift Certificate id should be correct")
    Long giftCertificateId;

}
