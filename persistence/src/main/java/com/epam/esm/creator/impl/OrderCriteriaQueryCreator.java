package com.epam.esm.creator.impl;

import com.epam.esm.creator.AbstractQueryCreator;
import com.epam.esm.creator.QueryCreator;
import com.epam.esm.criteria.OrderCriteria;
import com.epam.esm.domain.Order;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static com.epam.esm.constant.GiftCertificateColumn.*;
import static com.epam.esm.constant.UserColumn.USERNAME;

@Component
public class OrderCriteriaQueryCreator extends AbstractQueryCreator<Order> implements QueryCreator<Order, OrderCriteria> {

    private static final String USER_MAPPING = "user";
    private static final String GIFT_CERTIFICATE_MAPPING = "giftCertificate";

    @Override
    public CriteriaQuery<Order> createGetQuery(OrderCriteria criteria, CriteriaBuilder criteriaBuilder, Class<Order> persistentClass) {
        CriteriaQuery<Order> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
        Root<Order> orderRoot = criteriaQuery.from(persistentClass);
        List<Predicate> restrictions = new ArrayList<>();

        if (criteria.getUsername() != null) {
            restrictions.add(addPredicateFromRelationalField(criteria.getUsername(), criteriaBuilder, orderRoot, USER_MAPPING, USERNAME));
        }
        if (criteria.getGiftCertificateName() != null) {
            restrictions.add(addPredicateFromRelationalField(criteria.getGiftCertificateName(), criteriaBuilder, orderRoot, GIFT_CERTIFICATE_MAPPING, NAME));
        }
        criteriaQuery.select(orderRoot).where(restrictions.toArray(new Predicate[]{}));

        addSortByField(criteria.getSortByOrderedDate(), criteriaBuilder, criteriaQuery, orderRoot, CREATE_DATE);
        addSortByField(criteria.getSortByOrderedDate(), criteriaBuilder, criteriaQuery, orderRoot, PRICE);

        return criteriaQuery;
    }

    private Predicate addPredicateFromRelationalField(Object field, CriteriaBuilder criteriaBuilder, Root<Order> orderRoot, String mappingTable, String columnName) {
        return criteriaBuilder.equal(orderRoot.join(mappingTable).get(columnName), field);
    }
}
