package com.epam.esm.repository.impl;

import com.epam.esm.creator.QueryCreator;
import com.epam.esm.criteria.UserCriteria;
import com.epam.esm.domain.User;
import com.epam.esm.repository.AbstractCrudRepository;
import com.epam.esm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public class UserRepositoryImpl extends AbstractCrudRepository<User, UserCriteria> implements UserRepository {

    private final EntityManager entityManager;

    @Autowired
    protected UserRepositoryImpl(QueryCreator<User, UserCriteria> creator, EntityManager entityManager) {
        super(User.class, creator);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return entityManager.createQuery(
                        "SELECT t FROM User t WHERE t.username = ?1",
                        User.class)
                .setParameter(1, username)
                .getResultList().stream().findFirst();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return entityManager.createQuery(
                        "SELECT t FROM User t WHERE t.email = ?1",
                        User.class)
                .setParameter(1, email)
                .getResultList()
                .stream().findFirst();
    }

    @Override
    public Optional<User> findByPhoneNumber(String phoneNumber) {
        return entityManager.createQuery(
                        "SELECT t FROM User  t WHERE t.phoneNumber = ?1",
                        User.class)
                .setParameter(1, phoneNumber)
                .getResultList()
                .stream().findFirst();
    }
}
