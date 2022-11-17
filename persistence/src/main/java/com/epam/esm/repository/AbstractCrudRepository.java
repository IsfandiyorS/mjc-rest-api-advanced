package com.epam.esm.repository;

import com.epam.esm.creator.QueryCreator;
import com.epam.esm.criteria.Criteria;
import com.epam.esm.domain.BaseAbstractDomain;
import com.epam.esm.enums.State;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class AbstractCrudRepository<T extends BaseAbstractDomain, C extends Criteria> {

    @PersistenceContext
    private EntityManager entityManager;
    private final Class<T> persistentClass;
    private final QueryCreator<T, C> creator;

    protected AbstractCrudRepository(Class<T> persistentClass, QueryCreator<T, C> creator) {
        this.persistentClass = persistentClass;
        this.creator = creator;
    }

    @Transactional
    public Long save(T entity) {
        if (entity == null) {
            return null;
        }
        entityManager.persist(entity);
        //fixme read about flush
        entityManager.flush();
        return entity.getId();
    }

    public Optional<T> findById(Long id) {
        try {
            return Optional.ofNullable(entityManager.createQuery(
                            "SELECT t FROM " + persistentClass.getSimpleName() +
                                    " t WHERE t.id = ?1 and t.state <> 2", persistentClass)
                    .setParameter(1, id)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public List<T> findAll(PageRequest pageRequest) {
        int startedNumber = pageRequest.getPageNumber() * pageRequest.getPageSize();
        return entityManager.createQuery(
                        "SELECT t FROM " + persistentClass.getSimpleName()
                                + " t WHERE t.state <> 2 ORDER BY t.id asc", persistentClass)
                .setFirstResult(startedNumber)
                .setMaxResults(pageRequest.getPageSize())
                .getResultList();
    }

    public List<T> find(C criteria, PageRequest pageable) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteriaQuery = creator.createGetQuery(criteria, criteriaBuilder, persistentClass);

        int startedNumber = pageable.getPageNumber() * pageable.getPageSize();
        return entityManager.createQuery(criteriaQuery)
                .setFirstResult(startedNumber)
                .setMaxResults(pageable.getPageSize())
                .getResultList();
    }

    public Query createQuery(StringBuilder queryBuilder) {
        return entityManager.createQuery("SELECT t FROM " + persistentClass.getSimpleName() +
                " t WHERE t.state <> 2 " + queryBuilder.toString(), persistentClass);
    }

    @Transactional
    public T update(T update) {
        return entityManager.merge(update);
    }

    /**
     * Method for removing an entity from a table by ID.
     */
    @Transactional
    public Long delete(T entity) {
        if (entity == null) {
            return null;
        }
        entity.setState(State.DELETED);
        return save(entity);
    }


}
