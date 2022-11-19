package com.epam.esm.service.impl.model;

import com.epam.esm.criteria.TagCriteria;
import com.epam.esm.domain.Tag;
import com.epam.esm.dto.certificate.TagCreateDto;
import com.epam.esm.dto.certificate.TagDto;

import java.util.List;

public final class TagModel {

    public static final Long TAG_ID = 1L;
    public static final Tag TAG_ENTITY = new Tag(1L, "tag1");
    public static final Tag CREATED_NEW_TAG_ENTITY = new Tag(6L, "tag_create");
    public static final TagDto CREATED_TAG_ENTITY = new TagDto(6L, "tag_create");
    public static final TagDto TAG_DTO = new TagDto(1L, "tag1");
    public static final TagCreateDto TAG_CREATE_DTO = new TagCreateDto("tag_create");
    public static final TagCriteria TAG_CRITERIA = new TagCriteria("tag1", "asc", "tag");

}
