package com.epam.esm.dao.impl;

import com.epam.esm.creator.QueryCreator;
import com.epam.esm.criteria.TagCriteria;
import com.epam.esm.dao.AbstractCrudDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.domain.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Class {@code TagDaoImpl} is implementation of interface
 * {@link TagDao} and intended to work with 'tags' table.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@Repository
public class TagDaoImpl extends AbstractCrudDao<Tag, TagCriteria> implements TagDao {
    private final QueryCreator queryCreator;

    @Autowired
    public TagDaoImpl(QueryCreator queryCreator) {
        super(Tag.class);
        this.queryCreator = queryCreator;
    }


    @Override
    public List<Tag> doFilter(Map<String, String> filterMap) {
        return null;
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Tag> getAttachedTagsWithGiftCertificateId(Long giftCertificateId) {
        return null;
    }

    @Override
    public boolean checkForAvailabilityOfTagIdInRelatedTable(Long tagId, Long giftCertificateId) {
        return false;
    }

//    @Override
//    public Long deleteById(Long id) throws ActionFallDaoException {
//        jdbcTemplate.update(DELETE_TAG_IN_TABLE_WITH_TAGS, id);
//        int deleteEntity = jdbcTemplate.update(DELETE_TAG_QUERY, id);
//        if (deleteEntity == 0){
//            throw new ActionFallDaoException(format(ErrorCodes.ACTION_FALL.message, DELETE));
//        }
//        return (long) deleteEntity;
//    }
//
//    @Override
//    public List<Tag> getAll() {
//        return jdbcTemplate.query(GET_ALL_TAG, tagMapper);
//    }
//
//    @Override
//    public Optional<Tag> findByName(String name) {
//        return jdbcTemplate.query(GET_TAG_BY_NAME, tagMapper, name).stream().findAny();
//    }
//
//    @Override
//    public List<Tag> getAttachedTagsWithGiftCertificateId(Long giftCertificateId) {
//        return jdbcTemplate.query(GET_ATTACHED_TAGS_BY_GIFT_CERTIFICATE_ID, tagMapper, giftCertificateId);
//    }
//
//    @Override
//    public boolean checkForAvailabilityOfTagIdInRelatedTable(Long tagId, Long giftCertificateId) {
//        List<Long> longList = jdbcTemplate.query(GET_TAG_ID_FROM_RELATED_TABLE, (rs, rowNum) -> rs.getLong(TAG_ID), tagId, giftCertificateId);
//        return longList.size() > 0;
//    }
//
//    @Override
//    public List<Tag> doFilter(Map<String, String> criteria) throws ObjectNotFoundDaoException {
//        String query = queryCreator.createGetTagQuery(criteria, TAG_TABLE, TAG_TABLE_NAME_IN_QUERY);
//        try {
//            return jdbcTemplate.query(query, new TagMapper());
//        } catch (DataAccessException e) {
//            throw new ObjectNotFoundDaoException(format(ErrorCodes.OBJECT_NOT_FOUND_BY_FIELD.message, FIELD));
//        }
//
//    }
}
