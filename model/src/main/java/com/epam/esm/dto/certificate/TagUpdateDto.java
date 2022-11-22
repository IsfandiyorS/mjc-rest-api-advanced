package com.epam.esm.dto.certificate;

import com.epam.esm.dto.GenericCrudDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class TagUpdateDto implements GenericCrudDto {

    @NotBlank(message = "Tag id must be valid")
    Long id;

    @NotBlank(message = "Tag name should be correct")
    String name;
}
