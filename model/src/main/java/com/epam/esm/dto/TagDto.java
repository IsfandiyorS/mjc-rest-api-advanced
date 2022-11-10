package com.epam.esm.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class TagDto extends GenericDto{

    String name;

    @Builder(builderMethodName = "tagBuilder")
    public TagDto(Long id, String name) {
        super(id);
        this.name = name;
    }
}
