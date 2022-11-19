package com.epam.esm.repository;

import com.epam.esm.criteria.GiftCertificateCriteria;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@code GiftCertificateDao} describes abstract behavior for
 * working with gift_certificate table in database.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
public interface GiftCertificateRepository extends GenericCrudRepository<GiftCertificate, GiftCertificateCriteria> {

//    /**
//     * Method for adding a list of tags by gift certificate ID to database.
//     *
//     * @param giftCertificateId ID of gift certificate to update
//     * @param tagId ID of tag to attach to gift certificate
//     */
//    void attachTagToGiftCertificate(List<Tag> tagId, Long giftCertificateId);

    /**
     * Method for getting a list of tags from a table with a specific name.
     *
     * @param name name of objects to get
     * @return Optional object of entit from table
     */
    Optional<GiftCertificate> findByName(String name);
}
