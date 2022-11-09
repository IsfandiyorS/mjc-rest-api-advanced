package com.epam.esm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * Abstract class {@code Tag} represents tag entity.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Tag extends BaseAbstractDomain {
    private String name;

    public Tag(Long id, String name){
        super(id);
        this.name = name;
    }
}
