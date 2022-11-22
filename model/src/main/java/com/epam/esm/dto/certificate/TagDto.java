package com.epam.esm.dto.certificate;

import com.epam.esm.dto.GenericDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class TagDto extends RepresentationModel<TagDto> implements GenericDto {

    Long id;
    String name;

}
