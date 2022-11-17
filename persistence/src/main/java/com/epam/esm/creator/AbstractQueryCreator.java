package com.epam.esm.creator;

import com.epam.esm.enums.SortType;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractQueryCreator<T> {
    private static final String PERCENT = "%";

    protected List<Predicate> addField(Object field, CriteriaBuilder criteriaBuilder, Root<T> root, String columnName) {
        List<Predicate> restrictions = new ArrayList<>();

        if (field != null) {
            restrictions.add(criteriaBuilder.equal(root.get(columnName), field));
        }
        return restrictions;
    }

    protected List<Predicate> addPartOfField(String field, CriteriaBuilder criteriaBuilder, Root<T> root, String columnName) {
        List<Predicate> restrictions = new ArrayList<>();

        if (field != null) {
            restrictions.add(criteriaBuilder.like(root.get(columnName), PERCENT + field + PERCENT));
        }
        return restrictions;
    }

    protected void addSortByField(String field, CriteriaBuilder criteriaBuilder, CriteriaQuery<T> criteriaQuery, Root<T> root, String columnName) {
        if (field != null) {
            if (Objects.equals(field.toUpperCase(), SortType.DESC.getSortTypeName())) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(columnName)));
            }
            if (Objects.equals(field.toUpperCase(), SortType.ASC.getSortTypeName())) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(columnName)));
            }
        }
    }

}
