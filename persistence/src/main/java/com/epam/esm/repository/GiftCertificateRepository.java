package com.epam.esm.repository;

import com.epam.esm.criteria.GiftCertificateCriteria;
import com.epam.esm.domain.GiftCertificate;

import java.util.Optional;

/**
 * Interface {@code GiftCertificateDao} describes abstract behavior for
 * working with gift_certificate table in database.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
public interface GiftCertificateRepository extends GenericCrudRepository<GiftCertificate, GiftCertificateCriteria> {

    /**
     * Method for getting a list of tags from a table with a specific name.
     *
     * @param name name of objects to get
     * @return Optional object of entit from table
     */
    Optional<GiftCertificate> findByName(String name);
}
