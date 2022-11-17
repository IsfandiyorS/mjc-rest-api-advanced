package com.epam.esm.service;

import com.epam.esm.criteria.Criteria;
import com.epam.esm.domain.BaseAbstractDomain;
import com.epam.esm.dto.GenericCrudDto;
import com.epam.esm.dto.GenericDto;
import org.springframework.data.domain.PageRequest;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Interface {@code GenericCrudService} describes CRUD operations for working with objects.
 *
 * @param <T>  the type parameter
 * @param <ID> the ID of object
 * @param <C>  Objects filter or sort class inherited from Criteria
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
public interface GenericCrudService<T extends BaseAbstractDomain, CD, UD extends GenericCrudDto, D extends GenericDto, ID extends Serializable, C extends Criteria> {

    /**
     * Method for getting object by its ID
     *
     * @param id ID of object which will send from url
     * @return <T> type of object returns from database
     */
    D getById(ID id);

    /**
     * Method for getting all object
     *
     * @return List of required object
     */
    List<D> getAll(PageRequest pageRequest);

    /**
     * Method for creating object by its specific fields
     *
     * @param createEntity request parameters from url
     * @return Long as an ID of object
     */
    Long create(CD createEntity);


    /**
     * Method for updating object or entity by its parameters as well as this method defined with
     * default access modifier because this method will be overridden if required
     *
     * @param updateEntity request parameters from url
     */
    default void update(UD updateEntity) {}

    /**
     * Method for deleting object or entity by its ID.
     *
     * @param id request parameters from url
     * @return Long as a response
     */
    int delete(ID id);

    /**
     * Method for getting a list of objects by specific parameters.
     *
     * @param criteria request parameters from url
     * @return List of required object
     */
    List<D> doFilter(C criteria, PageRequest pageable);

    /**
     * Method for validating optional object if it exists or not in database.
     *
     * @param entity object returned from database
     */
    void validate(Optional<T> entity);

}