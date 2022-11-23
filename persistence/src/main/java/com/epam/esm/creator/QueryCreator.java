package com.epam.esm.creator;

import com.epam.esm.criteria.Criteria;
import com.epam.esm.domain.Auditable;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Class {@code QueryCreator} designed to create a selection condition.
 *
 * @param <T> the type parameter
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
public interface QueryCreator<T extends Auditable, C extends Criteria> {

    /**
     * @param criteriaBuilder the criteria builder
     * @return the criteria query
     */
    CriteriaQuery<T> createGetQuery(C criteria, CriteriaBuilder criteriaBuilder, Class<T> persistentClass);
}
