package com.epam.esm.repository.impl;

import com.epam.esm.creator.QueryCreator;
import com.epam.esm.criteria.TagCriteria;
import com.epam.esm.domain.Tag;
import com.epam.esm.repository.AbstractCrudRepository;
import com.epam.esm.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 * Class {@code TagDaoImpl} is implementation of interface
 * {@link TagRepository} and intended to work with 'tags' table.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@Repository
public class TagRepositoryImpl extends AbstractCrudRepository<Tag, TagCriteria> implements TagRepository{
    private final EntityManager entityManager;

    private static final String FIND_MOST_WIDELY_USED_TAG_OF_USER_WITH_HIGHEST_COST_OF_ALL_ORDERS_QUERY = """
            SELECT t FROM GiftCertificate g INNER JOIN g.tagList t WHERE g.id IN
            (SELECT o.giftCertificate.id FROM Order o
            WHERE o.user.id IN
            (SELECT o.user.id FROM Order o GROUP BY o.user.id ORDER BY SUM(o.price) DESC))
            GROUP BY t.id ORDER BY COUNT(t.id) DESC""";


    @Autowired
    public TagRepositoryImpl(EntityManager entityManager, QueryCreator<Tag, TagCriteria> creator) {
        super(Tag.class, creator);
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Tag> findByName(String name) {
        return entityManager.createQuery(
                        "SELECT t FROM Tag as t WHERE t.name = ?1", Tag.class)
                .setParameter(1, name)
                .getResultList().stream().findFirst();
    }

    @Override
    public List<Tag> findMostPopular() {
        return entityManager.createQuery(
                        FIND_MOST_WIDELY_USED_TAG_OF_USER_WITH_HIGHEST_COST_OF_ALL_ORDERS_QUERY,
                        Tag.class)
                .getResultList();
    }

}
