package com.epam.esm.service.impl;

import com.epam.esm.criteria.TagCriteria;
import com.epam.esm.dao.TagDao;
import com.epam.esm.domain.Tag;
import com.epam.esm.enums.ErrorCodes;
import com.epam.esm.exceptions.AlreadyExistException;
import com.epam.esm.exceptions.ObjectNotFoundException;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.epam.esm.constant.FilterParameters.*;
import static java.lang.String.format;


/**
 * Class {@code TagServiceImpl} is implementation of interface {@link TagService}
 * and intended to work with {@link Tag} objects.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@Service
public class TagServiceImpl implements TagService {

    private final TagDao tagDao;

    @Autowired
    public TagServiceImpl(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    @Override
    public Tag getById(Long id) throws ObjectNotFoundException {
        Optional<Tag> optionalTag = tagDao.findById(id);
        validate(optionalTag);
        return optionalTag.get();
    }

    @Override
    public List<Tag> getAll() {
        return tagDao.findAll(PageRequest.of(1, 5));
    }

    @Override
    public Long create(Tag createEntity) {

//        TagValidator.isCreateEntityValid(createEntity);

        Optional<Tag> optionalTag = tagDao.findByName(createEntity.getName());

        if (optionalTag.isPresent()) {
            throw new AlreadyExistException(format(ErrorCodes.OBJECT_ALREADY_EXIST.message));
        }

        return tagDao.save(createEntity);
    }

    @Override
    public Boolean delete(Long id) {
        Tag tag = getById(id);
        Long deleteId = tagDao.delete(tag);
        return Objects.equals(tag.getId(), deleteId);
    }

    @Override
    public List<Tag> doFilter(TagCriteria criteria) {
        Map<String, String> tagFilterMap = new HashMap<>();
        tagFilterMap.put(TAG_NAME, criteria.getTagName());
        tagFilterMap.put(SORT_BY_TAG_NAME, criteria.getSortByTagName());
        tagFilterMap.put(PART_OF_TAG_NAME, criteria.getPartOfTagName());
        return tagDao.doFilter(tagFilterMap);
    }

    @Override
    public void validate(Optional<Tag> entity) throws ObjectNotFoundException {
        if (entity.isEmpty()) {
            throw new ObjectNotFoundException(format(ErrorCodes.OBJECT_NOT_FOUND_ID.message));
        }
    }

}
