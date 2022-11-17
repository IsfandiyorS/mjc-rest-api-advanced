package com.epam.esm.service;

import com.epam.esm.criteria.TagCriteria;
import com.epam.esm.domain.Tag;
import com.epam.esm.dto.certificate.TagCreateDto;
import com.epam.esm.dto.certificate.TagDto;
import com.epam.esm.dto.certificate.TagUpdateDto;

/**
 * Interface {@code TagService} describes abstract behavior for working with {@link Tag} objects.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
public interface TagService extends GenericCrudService<Tag, TagCreateDto, TagUpdateDto, TagDto, Long, TagCriteria> {
}
