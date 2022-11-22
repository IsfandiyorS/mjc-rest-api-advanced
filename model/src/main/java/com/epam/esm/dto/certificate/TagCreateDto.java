package com.epam.esm.dto.certificate;

import com.epam.esm.dto.GenericCrudDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class TagCreateDto implements GenericCrudDto {

    @NotBlank(message = "Tag name should be correct")
    String name;
}
