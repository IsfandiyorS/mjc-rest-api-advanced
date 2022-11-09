package com.epam.esm.dao;

import com.epam.esm.criteria.Criteria;
import com.epam.esm.domain.BaseAbstractDomain;
import com.epam.esm.enums.State;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public abstract class AbstractCrudDao<T extends BaseAbstractDomain, C extends Criteria> {

    @PersistenceContext
    private EntityManager entityManager;
    private final Class<T> persistentClass;

    // fixme about SuppressWarnings
    protected AbstractCrudDao(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

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
        return Optional.ofNullable(entityManager.createQuery(
                        "SELECT t FROM " + persistentClass.getSimpleName() +
                                "as t WHERE t.id = ?1 and t.state <> 2", persistentClass)
                .setParameter(1, id)
                .getSingleResult());
    }

    public List<T> findAll(PageRequest pageRequest) {
        return entityManager.createQuery(
                        "SELECT t FROM " + persistentClass.getSimpleName()
                                + " as t WHERE t.state <> 2", persistentClass)
                .setFirstResult(pageRequest.getPageNumber())
                .setMaxResults(pageRequest.getPageSize())
                .getResultList();
    }

//    public Optional<T> find(C criteria) {
//        return Optional.ofNullable();
//    }

    @Transactional
    public T update(T update) {
        return entityManager.merge(update);
    }

    /**
     * Method for removing an entity from a table by ID.
     *
     */
    @Transactional
    public Long delete(T entity) {
        if (entity == null) {
            return null;
        }
        entity.setState(State.DELETED);
        return entityManager.merge(entity).getId();
    }
}
