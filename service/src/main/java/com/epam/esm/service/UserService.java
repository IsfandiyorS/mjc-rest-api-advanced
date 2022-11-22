package com.epam.esm.service;

import com.epam.esm.criteria.UserCriteria;
import com.epam.esm.domain.User;
import com.epam.esm.dto.auth.UserCreateDto;
import com.epam.esm.dto.auth.UserDto;
import com.epam.esm.dto.auth.UserUpdateDto;

public interface UserService extends GenericCrudService<User, UserCreateDto, UserUpdateDto, UserDto, Long, UserCriteria> {
}
