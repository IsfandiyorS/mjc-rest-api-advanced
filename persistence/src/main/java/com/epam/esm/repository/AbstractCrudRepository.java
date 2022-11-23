package com.epam.esm.repository;

import com.epam.esm.creator.QueryCreator;
import com.epam.esm.criteria.Criteria;
import com.epam.esm.domain.Auditable;
import com.epam.esm.enums.State;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public abstract class AbstractCrudRepository<T extends Auditable, C extends Criteria> {

    @PersistenceContext
    private EntityManager entityManager;
    private final Class<T> persistentClass;
    private final QueryCreator<T, C> creator;

    protected AbstractCrudRepository(Class<T> persistentClass, QueryCreator<T, C> creator) {
        this.persistentClass = persistentClass;
        this.creator = creator;
    }

    public T save(T entity) {
        if (entity == null) {
            return null;
        }
        entityManager.persist(entity);
        return entity;
    }

    public Optional<T> findById(Long id) {
        return Optional.ofNullable(
                entityManager.find(persistentClass, id)
        );
    }

    public List<T> findAll(PageRequest pageRequest) {
        int startedNumber = pageRequest.getPageNumber() * pageRequest.getPageSize();

        return entityManager.createQuery(
                        "SELECT t FROM " + persistentClass.getSimpleName()
                                + " t ORDER BY t.id asc", persistentClass)
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

    public T update(T update) {
        update.setState(State.UPDATED);
        return entityManager.merge(update);
    }

    public Long delete(T entity) {
        if (entity == null) {
            return null;
        }
        entity.setState(State.DELETED);
        entityManager.persist(entity);
        return (long) entity.getState().ordinal();
    }


}
