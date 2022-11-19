package com.epam.esm.repository.impl;

import com.epam.esm.creator.QueryCreator;
import com.epam.esm.criteria.GiftCertificateCriteria;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.repository.AbstractCrudRepository;
import com.epam.esm.repository.GiftCertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Optional;

/**
 * Class {@code GiftCertificateDaoImpl} is implementation of interface {@link GiftCertificateRepository}
 * and intended to work with 'gift_certificate' table.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@Repository
public class GiftCertificateRepositoryImpl extends AbstractCrudRepository<GiftCertificate, GiftCertificateCriteria> implements GiftCertificateRepository {
    private final EntityManager entityManager;

    @Autowired
    public GiftCertificateRepositoryImpl(QueryCreator<GiftCertificate, GiftCertificateCriteria> creator, EntityManager entityManager) {
        super(GiftCertificate.class, creator);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<GiftCertificate> findByName(String name) {
        try {
            return Optional.of(
                    entityManager.createQuery(
                                    "SELECT t FROM GiftCertificate as t WHERE t.name = ?1 and t.state <> 2",
                                    GiftCertificate.class)
                            .setParameter(1, name)
                            .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public GiftCertificate update(GiftCertificate update) {
        if (update == null) {
            return null;
        }
        entityManager.persist(update);
        return update;
    }
}
