package com.epam.esm.service.impl.model;

import com.epam.esm.criteria.UserCriteria;
import com.epam.esm.domain.User;
import com.epam.esm.dto.auth.UserCreateDto;
import com.epam.esm.dto.auth.UserDto;
import com.epam.esm.dto.auth.UserUpdateDto;
import org.springframework.data.domain.PageRequest;

import static com.epam.esm.enums.UserType.USER;

public final class UserModel {
    public static final PageRequest PAGE_REQUEST=PageRequest.of(0, 5);
    public static final Long USER_ID = 1L;
    public static final User USER_ENTITY = new User(1L,"user1", "ali", "ali", "gali@mail.ru", "ali1ali1", "998903353875", USER, null);
    public static final User NEW_ENTITY = new User("user3", "ali", "ali", "gali3@mail.ru", "ali1ali1", "998903353879", USER, null);
    public static final User CREATED_ENTITY = new User(3L,"user3", "ali", "ali", "gali3@mail.ru", "ali1ali1", "998903353879", USER, null);
    public static final User UPDATED_ENTITY = new User(1L,"user1", "ali", "ali", "gali12@mail.ru", "ali1ali1", "998903353879", USER, null);
    public static final UserDto USER_DTO = new UserDto(1L, "user1", "ali", "ali", "gali@mail.ru", "998903353875", USER);
    public static final UserCreateDto USER_CREATE_DTO = new UserCreateDto("user3", "ali", "ali", "gali3@mail.ru", "ali1ali1", "998903353879");
    public static final UserDto CREATED_USER_DTO = new UserDto(3L, "user3", "ali", "ali", "gali3@mail.ru", "998903353879", USER);
    public static final UserUpdateDto USER_UPDATE_DTO = new UserUpdateDto(1L, null, null, null, "gali12@mail.ru", null, null);
    public static final UserDto UPDATED_USER_DTO = new UserDto(1L, "user1", "ali", "ali", "gali12@mail.ru", "998903353879", USER);
    public static final UserCriteria USER_CRITERIA = new UserCriteria(null, null, null, null, "998903353875");
}
