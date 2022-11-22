package com.epam.esm.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;

import static lombok.AccessLevel.PRIVATE;

/**
 * Abstract class {@code Tag} represents tag entity.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@Entity
@Data
@Builder
@Where(clause = "state <> 2")
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
        return "Tag{" +
                "name='" + name + '\'' +
                '}';
    }
}
