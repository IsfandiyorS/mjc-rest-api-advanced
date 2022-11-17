package com.epam.esm.repository;

import com.epam.esm.criteria.Criteria;
import com.epam.esm.domain.BaseAbstractDomain;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;
import java.util.Optional;

// fixme change all of them to another
/**
 * Interface {@code GenericCrudDao} describes CRUD operations for working with database tables.
 *
 * @param <T> the type parameter
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
public interface GenericCrudRepository<T extends BaseAbstractDomain, C extends Criteria> {

    /**
     * Method for saving an entity to a table.
     *
     * @param entity entity object to save
     */
    Long save(T entity);

    /**
     * Method for updating an entity in a table if updating required.
     *
     * @param entity is an object to update
     */
    T update(T entity);
    /**
     * Method for removing an entity from a table by ID.
     *
     * @param entity is an entity to remove
     */
    Long delete(T entity);

    /**
     * Method for getting an entity object from a table by ID.
     *
     * @param id ID of entity to get
     * @return Entity object from table
     */
    Optional<T> findById(Long id);

    /**
     * Method for getting all entities from a table.
     *
     * @return List of all entities in the table
     */
    List<T> findAll(PageRequest request);

    /**
     * Method for filtering elements.
     *
     * @param criteria contains fields will be sanded to filter or sort
     * @return List of objects by filtering parameters
     */
    List<T> find(C criteria, PageRequest pageRequest);
}
