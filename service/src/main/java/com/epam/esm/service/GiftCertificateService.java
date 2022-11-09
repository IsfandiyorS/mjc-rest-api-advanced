package com.epam.esm.service;

import com.epam.esm.criteria.GiftCertificateCriteria;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.domain.Tag;

import java.util.List;

/**
 * Interface {@code GiftCertificateService} describes abstract behavior for
 * working with {@link GiftCertificate} objects.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
public interface GiftCertificateService extends GenericCrudService<GiftCertificate, Long, GiftCertificateCriteria> {

    /**
     * Method for getting a list of tags by gift certificate ID.
     *
     * @param giftCertificateId ID of gift certificate
     * @return List of tags from gift certificate
     */
    List<Tag> getAttachedTagsWithGiftCertificateId(Long giftCertificateId);

    /**
     * Method for adding a list of tags.
     *
     * @param giftCertificateId ID of gift certificate
     * @param tags              list of tags to add
     */
    void attachTagsToGiftCertificate(Long giftCertificateId, List<Tag> tags);

    /**
     * Method for deleting a list of tags.
     *
     * @param id      ID of gift certificate
     * @param tagList list of tags to delete
     * @return Long as a row number
     */
    Long deleteAssociatedTags(Long id, List<Tag> tagList);

}
