package com.epam.esm.repository.impl;

import com.epam.esm.creator.QueryCreator;
import com.epam.esm.criteria.GiftCertificateCriteria;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.repository.AbstractCrudRepository;
import com.epam.esm.repository.GiftCertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Class {@code GiftCertificateDaoImpl} is implementation of interface {@link GiftCertificateRepository}
 * and intended to work with 'gift_certificate' table.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@Repository
@Transactional
public class GiftCertificateRepositoryImpl extends AbstractCrudRepository<GiftCertificate, GiftCertificateCriteria> implements GiftCertificateRepository {
    private final EntityManager entityManager;

    @Autowired
    public GiftCertificateRepositoryImpl(QueryCreator<GiftCertificate, GiftCertificateCriteria> creator, EntityManager entityManager) {
        super(GiftCertificate.class, creator);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<GiftCertificate> findByName(String name) {
        return entityManager.createQuery(
                        "SELECT t FROM GiftCertificate as t WHERE t.name = ?1",
                        GiftCertificate.class)
                .setParameter(1, name)
                .getResultList().stream().findFirst();

    }
}
