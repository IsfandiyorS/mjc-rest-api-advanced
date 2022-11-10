package com.epam.esm.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;

import static lombok.AccessLevel.*;

/**
 * Abstract class {@code Tag} represents tag entity.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@Entity
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Tag extends BaseAbstractDomain {
    String name;
}
