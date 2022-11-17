package com.epam.esm.creator.impl;

import com.epam.esm.creator.AbstractQueryCreator;
import com.epam.esm.creator.QueryCreator;
import com.epam.esm.criteria.TagCriteria;
import com.epam.esm.domain.Tag;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static com.epam.esm.constant.TagColumn.NAME;

@Component
public class TagCriteriaQueryImpl extends AbstractQueryCreator<Tag> implements QueryCreator<Tag, TagCriteria> {

    @Override
    public CriteriaQuery<Tag> createGetQuery(TagCriteria criteria, CriteriaBuilder criteriaBuilder, Class<Tag> persistentClass) {
        CriteriaQuery<Tag> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
        Root<Tag> tagRoot = criteriaQuery.from(persistentClass);
        List<Predicate> restrictions = new ArrayList<>();

        restrictions.addAll(addField(criteria.getTagName(), criteriaBuilder, tagRoot, NAME));
        restrictions.addAll(addPartOfField(criteria.getPartOfTagName(), criteriaBuilder, tagRoot, NAME));
        criteriaQuery.select(tagRoot).where(restrictions.toArray(new Predicate[]{}));

        addSortByField(criteria.getSortByTagName(), criteriaBuilder, criteriaQuery, tagRoot, NAME);
        return criteriaQuery;
    }
}
