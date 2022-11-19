package com.epam.esm.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;

import java.util.StringJoiner;

import static lombok.AccessLevel.*;

/**
 * Abstract class {@code Tag} represents tag entity.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@Entity
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Tag extends BaseAbstractDomain {
    String name;

    public Tag(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Tag.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
