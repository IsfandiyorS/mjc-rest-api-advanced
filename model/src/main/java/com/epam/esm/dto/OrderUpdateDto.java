package com.epam.esm.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class OrderUpdateDto extends GenericCrudDto{


    // fixme i dont know what fields should be updated
    Integer orderQuantity;

    Long userId;

    Long giftCertificateId;
}
