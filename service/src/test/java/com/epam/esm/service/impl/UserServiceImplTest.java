package com.epam.esm.service.impl;

import com.epam.esm.domain.User;
import com.epam.esm.dto.auth.UserDto;
import com.epam.esm.enums.ErrorCodes;
import com.epam.esm.exceptions.AlreadyExistException;
import com.epam.esm.exceptions.ObjectNotFoundException;
import com.epam.esm.mapper.auth.UserMapper;
import com.epam.esm.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.epam.esm.constant.UserColumn.USER;
import static com.epam.esm.constant.UserColumn.USERNAME;
import static com.epam.esm.service.impl.domain.UserModel.*;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @Test
    void getById() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(USER_ENTITY));

        UserDto actualUserDto = userService.getById(USER_ID);

        assertEquals(USER_DTO, actualUserDto);
        verify(userRepository, times(1)).findById(USER_ID);
    }

    @Test
    void testGetByIdMethodThrowObjectNotFoundException() {
        when(userRepository.findById(NOT_AVAILABLE_USER_ID))
                .thenReturn(Optional.empty());

        ObjectNotFoundException objectNotFoundException =
                assertThrows(ObjectNotFoundException.class, () -> userService.getById(NOT_AVAILABLE_USER_ID));

        String expectedException = format(ErrorCodes.OBJECT_NOT_FOUND_ID.message, USER);
        String actualException = objectNotFoundException.getMessage();

        assertEquals(expectedException, actualException);

        verify(userRepository, times(1)).findById(NOT_AVAILABLE_USER_ID);
    }


    @Test
    void getAll() {
        when(userRepository.findAll(PAGE_REQUEST)).thenReturn(List.of(USER_ENTITY));

        List<UserDto> actualUserDto = userService.getAll(PAGE_REQUEST);

        assertNotNull(actualUserDto);
        verify(userRepository, times(1)).findAll(PAGE_REQUEST);
    }

    @Test
    void create() {
        when(userRepository.findByUsername(NEW_ENTITY.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(NEW_ENTITY.getEmail())).thenReturn(Optional.empty());
        when(userRepository.findByPhoneNumber(NEW_ENTITY.getPhoneNumber())).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(CREATED_ENTITY);

        UserDto actualDto = userService.create(USER_CREATE_DTO);

        assertEquals(CREATED_USER_DTO, actualDto);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testCreateMethodWithAvailableUsername() {
        when(userRepository.findByUsername(USER_WITH_AVAILABLE_CREATE_DTO.getUsername())).thenReturn(Optional.of(USER_ENTITY));

        AlreadyExistException alreadyExistException = assertThrows(AlreadyExistException.class,
                () -> userService.create(USER_WITH_AVAILABLE_CREATE_DTO));

        String expectedException = format(ErrorCodes.OBJECT_ALREADY_EXIST.message, USER, USERNAME);
        String actualException = alreadyExistException.getMessage();

        assertEquals(expectedException, actualException);

        verify(userRepository, times(1)).findByUsername(USER_WITH_AVAILABLE_CREATE_DTO.getUsername());
    }

    @Test
    void testUpdate() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(USER_ENTITY));
        lenient().when(userRepository.findByUsername(USER_UPDATE_DTO.getUsername())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(USER_UPDATE_DTO.getEmail())).thenReturn(Optional.empty());
        lenient().when(userRepository.findByPhoneNumber(USER_UPDATE_DTO.getPhoneNumber())).thenReturn(Optional.empty());
        when((userRepository.update(any(User.class)))).thenReturn(UPDATED_ENTITY);

        UserDto actualUpdatedDto = userService.update(USER_UPDATE_DTO);

        assertEquals(UPDATED_USER_DTO, actualUpdatedDto);

        verify(userRepository, times(1)).update(any(User.class));
    }

    @Test
    void doFilter() {
        when(userRepository.find(USER_CRITERIA, PAGE_REQUEST)).thenReturn(List.of(USER_ENTITY));

        List<UserDto> actualDtoList = userService.doFilter(USER_CRITERIA, PAGE_REQUEST);

        assertNotNull(actualDtoList);
        verify(userRepository, times(1)).find(USER_CRITERIA, PAGE_REQUEST);
    }

    @Test
    void delete() {
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(USER_ENTITY));
        when(userRepository.delete(USER_ENTITY)).thenReturn(DELETED_STATE);

        int actualId = userService.delete(USER_ID);

        assertEquals(1, actualId);
        verify(userRepository, times(1)).delete(any());
    }

    @Test
    void validate() {
        ObjectNotFoundException objectNotFoundException =
                assertThrows(ObjectNotFoundException.class, () -> userService.validate(Optional.empty()));
        String expectedException = format(ErrorCodes.OBJECT_NOT_FOUND.message, USER);
        String actualException = objectNotFoundException.getMessage();
        assertEquals(expectedException, actualException);
    }

    @Test
    void baseValidation() {
        AlreadyExistException objectNotFoundException =
                assertThrows(AlreadyExistException.class, () -> userService.baseValidation(Optional.of(USER_ENTITY), USERNAME));
        String expectedException = format(ErrorCodes.OBJECT_ALREADY_EXIST.message, USER, USERNAME);
        String actualException = objectNotFoundException.getMessage();
        assertEquals(expectedException, actualException);
    }
}