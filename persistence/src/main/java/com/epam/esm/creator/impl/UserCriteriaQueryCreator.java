package com.epam.esm.creator.impl;

import com.epam.esm.creator.AbstractQueryCreator;
import com.epam.esm.creator.QueryCreator;
import com.epam.esm.criteria.UserCriteria;
import com.epam.esm.domain.User;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

import static com.epam.esm.constant.UserColumn.*;

@Component
public class UserCriteriaQueryCreator extends AbstractQueryCreator<User> implements QueryCreator<User, UserCriteria> {

    @Override
    public CriteriaQuery<User> createGetQuery(UserCriteria criteria, CriteriaBuilder criteriaBuilder, Class<User> persistentClass) {
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
        Root<User> userRoot = criteriaQuery.from(persistentClass);
        List<Predicate> restrictions = new ArrayList<>();

        restrictions.addAll(addField(criteria.getUsername(), criteriaBuilder, userRoot, USERNAME));
        restrictions.addAll(addField(criteria.getEmail(), criteriaBuilder, userRoot, EMAIL));
        restrictions.addAll(addField(criteria.getFirstName(), criteriaBuilder, userRoot, FIRST_NAME));
        restrictions.addAll(addField(criteria.getLastName(), criteriaBuilder, userRoot, LAST_NAME));
        restrictions.addAll(addField(criteria.getPhoneNumber(), criteriaBuilder, userRoot, PHONE_NUMBER));
        criteriaQuery.select(userRoot).where(restrictions.toArray(new Predicate[]{}));
        return criteriaQuery;
    }
}
