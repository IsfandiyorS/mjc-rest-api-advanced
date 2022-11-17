package com.epam.esm.service.impl;

import com.epam.esm.criteria.UserCriteria;
import com.epam.esm.domain.User;
import com.epam.esm.dto.auth.UserCreateDto;
import com.epam.esm.dto.auth.UserDto;
import com.epam.esm.dto.auth.UserUpdateDto;
import com.epam.esm.enums.UserType;
import com.epam.esm.exceptions.AlreadyExistException;
import com.epam.esm.exceptions.ObjectNotFoundException;
import com.epam.esm.mapper.auth.UserMapper;
import com.epam.esm.repository.UserRepository;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    @Override
    public UserDto getById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new ObjectNotFoundException(format(USER_NOT_FOUND.message, userId));
        }
        return userMapper.toDto(optionalUser.get());
    }

    @Override
    public List<UserDto> getAll(PageRequest pageRequest) {
        return userMapper.toDtoList(userRepository.findAll(pageRequest));
    }

    @Override
    public Long create(UserCreateDto createEntity) {

        Optional<User> userByUsername = userRepository.findByUsername(createEntity.getUsername());
        baseValidation(userByUsername, USERNAME);

        Optional<User> userByEmail = userRepository.findByEmail(createEntity.getEmail());
        baseValidation(userByEmail, EMAIL);

        Optional<User> userByPhoneNumber = userRepository.findByPhoneNumber(createEntity.getPhoneNumber());
        baseValidation(userByPhoneNumber, PHONE_NUMBER);

        createEntity.setPassword(passwordEncoder.encode(createEntity.getPassword()));

        createEntity.setUserType(UserType.USER);
        return userRepository.save(userMapper.fromCreateDto(createEntity));
    }

    @Override
    public void update(UserUpdateDto updateDto) {

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

        if (!isEmptyObject(updateDto.getPassword())) {
            updateDto.setPassword(passwordEncoder.encode(updateDto.getPassword()));
        }

        userRepository.update(userMapper.fromUpdateDto(updateDto, optionalUser.get()));
    }

    @Override
    public int delete(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        validate(optionalUser);

        Long deleteId = userRepository.delete(optionalUser.get());
        return Objects.equals(optionalUser.get().getId(), deleteId) ? 1 : 0;
    }

    @Override
    public List<UserDto> doFilter(UserCriteria criteria, PageRequest pageable) {
        return userMapper.toDtoList(userRepository.find(criteria, pageable));
    }

    @Override
    public void validate(Optional<User> entity) {
        if (entity.isEmpty()) {
            throw new ObjectNotFoundException(format(OBJECT_NOT_FOUND_ID.message, "User"));
        }
    }

    public void baseValidation(Optional<User> entity, String field) {
        if (entity.isPresent()) {
            throw new AlreadyExistException(format(USER_ALREADY_EXIST_BY_FIELD.message, field));
        }
    }
}
