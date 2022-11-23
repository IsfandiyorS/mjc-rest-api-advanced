package com.epam.esm.service.impl;

import com.epam.esm.criteria.UserCriteria;
import com.epam.esm.domain.Order;
import com.epam.esm.domain.User;
import com.epam.esm.dto.auth.UserCreateDto;
import com.epam.esm.dto.auth.UserDto;
import com.epam.esm.dto.auth.UserUpdateDto;
import com.epam.esm.exceptions.AlreadyExistException;
import com.epam.esm.exceptions.ObjectNotFoundException;
import com.epam.esm.mapper.auth.UserMapper;
import com.epam.esm.repository.UserRepository;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.epam.esm.constant.UserColumn.*;
import static com.epam.esm.enums.ErrorCodes.*;
import static com.epam.esm.utils.BaseUtils.isEmptyObject;
import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto getById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new ObjectNotFoundException(format(OBJECT_NOT_FOUND_ID.message, USER, userId));
        }
        return userMapper.toDto(optionalUser.get());
    }

    @Override
    public List<UserDto> getAll(PageRequest pageRequest) {
        return userMapper.toDtoList(userRepository.findAll(pageRequest));
    }

    @Override
    public UserDto create(UserCreateDto createEntity) {

        Optional<User> userByUsername = userRepository.findByUsername(createEntity.getUsername());
        baseValidation(userByUsername, USERNAME);

        Optional<User> userByEmail = userRepository.findByEmail(createEntity.getEmail());
        baseValidation(userByEmail, EMAIL);

        Optional<User> userByPhoneNumber = userRepository.findByPhoneNumber(createEntity.getPhoneNumber());
        baseValidation(userByPhoneNumber, PHONE_NUMBER);

        User savedUser = userRepository.save(userMapper.fromCreateDto(createEntity));
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDto update(UserUpdateDto updateDto) {

        Optional<User> optionalUser = userRepository.findById(updateDto.getId());
        validate(optionalUser);

        if (!isEmptyObject(updateDto.getUsername())) {
            Optional<User> userByUsername = userRepository.findByUsername(updateDto.getUsername());
            baseValidation(userByUsername, USERNAME);
        }

        if (!isEmptyObject(updateDto.getEmail())) {
            Optional<User> userByEmail = userRepository.findByEmail(updateDto.getEmail());
            baseValidation(userByEmail, EMAIL);
        }
        if (!isEmptyObject(updateDto.getPhoneNumber())) {
            Optional<User> userByPhoneNumber = userRepository.findByPhoneNumber(updateDto.getPhoneNumber());
            baseValidation(userByPhoneNumber, PHONE_NUMBER);
        }

        User updatedUser = userRepository.update(userMapper.fromUpdateDto(updateDto, optionalUser.get()));
        return userMapper.toDto(updatedUser);
    }
 // fixme
    @Override
    public int delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        validate(optionalUser);

        Long deleteState = userRepository.delete(optionalUser.get());
        return deleteState == 2 ? 1 : 0;
    }

    @Override
    public List<UserDto> doFilter(UserCriteria criteria, PageRequest pageable) {
        return userMapper.toDtoList(userRepository.find(criteria, pageable));
    }

    @Override
    public void validate(Optional<User> entity) {
        if (entity.isEmpty()) {
            throw new ObjectNotFoundException(format(OBJECT_NOT_FOUND.message, USER));
        }
    }

    public void baseValidation(Optional<User> entity, String field) {
        if (entity.isPresent()) {
            throw new AlreadyExistException(format(OBJECT_ALREADY_EXIST.message, USER, field));
        }
    }
}
