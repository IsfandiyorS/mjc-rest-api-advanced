package com.epam.esm.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class OrderCreateDto extends GenericCrudDto {

    @Min(value = 1, message = "Order quantity should be more or equal to 1")
    Integer orderQuantity;

    @NotBlank
    @Min(value = 1, message = "User id should be correct")
    Long userId;

    @NotBlank
    @Min(value = 1, message = "Gift Certificate id should be correct")
    Long giftCertificateId;

}
