package com.epam.esm.service.impl.domain;

import com.epam.esm.criteria.GiftCertificateCriteria;
import com.epam.esm.domain.GiftCertificate;
import com.epam.esm.dto.certificate.GiftCertificateCreateDto;
import com.epam.esm.dto.certificate.GiftCertificateDto;
import com.epam.esm.dto.certificate.GiftCertificateUpdateDto;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

import static com.epam.esm.service.impl.domain.TagModel.*;

public final class GiftCertificateModel {

    public static final Long CERTIFICATE_ID = 1L;
    public static final Long NOT_AVAILABLE_GIFT_CERTIFICATE_ID = 1000L;

    public static final PageRequest PAGE_REQUEST = PageRequest.of(0, 5);

    public static final GiftCertificate ENTITY = new
            GiftCertificate(1L, "giftCertificate1", "description1", BigDecimal.valueOf(10.1), 1, List.of(TAG_ENTITY));

    public static final GiftCertificateDto GIFT_CERTIFICATE_DTO = new
            GiftCertificateDto(1L, "giftCertificate1", "description1", BigDecimal.valueOf(10.1), 1, List.of(TAG_DTO));

    public static final GiftCertificateDto CREATED_GIFT_CERTIFICATE_DTO = new
            GiftCertificateDto(4L, "giftCertificate4", "description4", BigDecimal.valueOf(40.4), 1, null);
    public static final GiftCertificateCreateDto CREATE_DTO = new
            GiftCertificateCreateDto("giftCertificate4", "description4", BigDecimal.valueOf(40.4), 4, null);

    public static final GiftCertificateCreateDto ALREADY_EXISTED_CERTIFICATE_DTO = new
            GiftCertificateCreateDto("giftCertificate1", "description1", BigDecimal.valueOf(10.1), 1, null);

    public static final GiftCertificateUpdateDto GIFT_CERTIFICATE_UPDATE_DTO = new
            GiftCertificateUpdateDto(1L, null, "description1_updated", null, null, null);

    public static final GiftCertificate UPDATE_GIFT_CERTIFICATE = new
            GiftCertificate(1L, "giftCertificate1", "description1_updated", BigDecimal.valueOf(10.1), 1, List.of(TAG_ENTITY));

    public static final GiftCertificateDto UPDATED_GIFT_CERTIFICATE = new
            GiftCertificateDto(1L, "giftCertificate1", "description1_updated", BigDecimal.valueOf(10.1), 1, List.of(TAG_DTO));

    public static final GiftCertificate NEW_GIFT_CERTIFICATE = new
            GiftCertificate(4L, "giftCertificate4", "description4", BigDecimal.valueOf(40.4), 4, null);

    public static final GiftCertificate CREATED_GIFT_CERTIFICATE = new
            GiftCertificate(4L, "giftCertificate4", "description4", BigDecimal.valueOf(40.4), 1, null);

    public static final GiftCertificateCriteria GIFT_CERTIFICATE_CRITERIA = new
            GiftCertificateCriteria("giftCertificate1", null, "desc", "asc", null, null);

}
