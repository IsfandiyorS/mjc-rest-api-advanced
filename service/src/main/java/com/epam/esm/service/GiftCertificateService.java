package com.epam.esm.service;

import com.epam.esm.criteria.GiftCertificateCriteria;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.dto.certificate.GiftCertificateCreateDto;
import com.epam.esm.dto.certificate.GiftCertificateDto;
import com.epam.esm.dto.certificate.GiftCertificateUpdateDto;

/**
 * Interface {@code GiftCertificateService} describes abstract behavior for
 * working with {@link GiftCertificate} objects.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
public interface GiftCertificateService extends GenericCrudService<GiftCertificate, GiftCertificateCreateDto, GiftCertificateUpdateDto, GiftCertificateDto, Long, GiftCertificateCriteria> {
}
