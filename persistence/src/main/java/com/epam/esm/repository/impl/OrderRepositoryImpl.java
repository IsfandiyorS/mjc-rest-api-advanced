package com.epam.esm.repository.impl;

import com.epam.esm.creator.QueryCreator;
import com.epam.esm.criteria.OrderCriteria;
import com.epam.esm.domain.Order;
import com.epam.esm.repository.AbstractCrudRepository;
import com.epam.esm.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl extends AbstractCrudRepository<Order, OrderCriteria> implements OrderRepository {
    private final EntityManager entityManager;
    private static final String ORDER_BY_USER_ID_SELF_ID = """
            SELECT o FROM Order o
                inner join
                User u
                on o.user.id=u.id
            WHERE o.id=?1 and u.id=?2""";


    @Autowired
    public OrderRepositoryImpl(QueryCreator<Order, OrderCriteria> creator, EntityManager entityManager) {
        super(Order.class, creator);
        this.entityManager = entityManager;
    }

    @Override
    public List<Order> findByUserId(PageRequest pageRequest, Long userId) {
//        try {
        return entityManager.createQuery("SELECT t FROM Order t WHERE t.user.id = ?1", Order.class)
                .setParameter(1, userId)
                .setFirstResult(pageRequest.getPageNumber())
                .setMaxResults(pageRequest.getPageSize())
                .getResultList();
//        } catch (NoResultException e) {
//            return null;
//        }
    }

    @Override
    public Optional<Order> findOrderByIdAndUserId(Long orderId, Long userId) {
        return entityManager.createQuery(ORDER_BY_USER_ID_SELF_ID, Order.class)
                .setParameter(1, orderId)
                .setParameter(2, userId)
                .getResultList().stream().findFirst();
    }
}
