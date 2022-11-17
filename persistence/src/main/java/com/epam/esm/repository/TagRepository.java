package com.epam.esm.repository;

import com.epam.esm.criteria.TagCriteria;
import com.epam.esm.domain.Tag;

import java.util.List;
import java.util.Optional;

/**
 * Interface {@code TagDao} describes abstract behavior for working with tags table in database.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
public interface TagRepository extends GenericCrudRepository<Tag, TagCriteria> {

    /**
     * Method for getting a list of tags from a table with a specific name.
     *
     * @param name name of tag to get
     * @return Optional object of tag from table
     */
    Optional<Tag> findByName(String name);

}
