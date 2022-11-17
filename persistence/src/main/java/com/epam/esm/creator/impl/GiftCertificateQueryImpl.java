package com.epam.esm.creator.impl;

import com.epam.esm.creator.AbstractQueryCreator;
import com.epam.esm.creator.QueryCreator;
import com.epam.esm.criteria.GiftCertificateCriteria;
import com.epam.esm.domain.GiftCertificate;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.epam.esm.constant.GiftCertificateColumn.*;

@Component
public class GiftCertificateQueryImpl extends AbstractQueryCreator<GiftCertificate> implements QueryCreator<GiftCertificate, GiftCertificateCriteria> {

    @Override
    public CriteriaQuery<GiftCertificate> createGetQuery(GiftCertificateCriteria giftCertificateCriteria, CriteriaBuilder criteriaBuilder, Class<GiftCertificate> persistentClass) {
        CriteriaQuery<GiftCertificate> criteriaQuery = criteriaBuilder.createQuery(persistentClass);
        Root<GiftCertificate> giftCertificateRoot = criteriaQuery.from(persistentClass);
        List<Predicate> restrictions = new ArrayList<>();

        restrictions.addAll(addField(giftCertificateCriteria.getName(), criteriaBuilder, giftCertificateRoot, NAME));
        restrictions.addAll(addTagNames(giftCertificateCriteria.getTagNameList(), criteriaBuilder, giftCertificateRoot));
        restrictions.addAll(addPartOfField(giftCertificateCriteria.getPartOfName(), criteriaBuilder, giftCertificateRoot, NAME));
        restrictions.addAll(addPartOfField(giftCertificateCriteria.getPartOfDescription(), criteriaBuilder, giftCertificateRoot, DESCRIPTION));
        criteriaQuery.select(giftCertificateRoot).where(restrictions.toArray(new Predicate[]{}));

        addSortByField(giftCertificateCriteria.getSortByName(), criteriaBuilder, criteriaQuery, giftCertificateRoot, NAME);
        addSortByField(giftCertificateCriteria.getSortByCreateDate(), criteriaBuilder, criteriaQuery, giftCertificateRoot, CREATE_DATE);

        return criteriaQuery;
    }

    private List<Predicate> addTagNames(List<String> tagNameList, CriteriaBuilder criteriaBuilder, Root<GiftCertificate> giftCertificateRoot) {
        List<Predicate> restrictions = new ArrayList<>();

        if (tagNameList != null) {
            restrictions = Arrays.stream(tagNameList.toArray())
                    .map(tagName -> criteriaBuilder.equal(giftCertificateRoot.join(TAG_LIST).get(NAME), tagName))
                    .collect(Collectors.toList());
        }
        return restrictions;
    }
}
