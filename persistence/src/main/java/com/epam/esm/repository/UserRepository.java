package com.epam.esm.repository;

import com.epam.esm.criteria.UserCriteria;
import com.epam.esm.domain.User;

import java.util.Optional;

public interface UserRepository extends GenericCrudRepository<User, UserCriteria> {

    Optional<User> findByUsername(final String username);

    Optional<User> findByEmail(final String email);

    Optional<User> findByPhoneNumber(final String phoneNumber);
}
