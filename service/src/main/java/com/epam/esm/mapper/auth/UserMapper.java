package com.epam.esm.mapper.auth;

import com.epam.esm.domain.User;
import com.epam.esm.dto.auth.UserCreateDto;
import com.epam.esm.dto.auth.UserDto;
import com.epam.esm.dto.auth.UserUpdateDto;
import com.epam.esm.mapper.BaseMapper;
import org.mapstruct.*;

import static org.mapstruct.NullValuePropertyMappingStrategy.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<User, UserCreateDto, UserUpdateDto, UserDto> {


    @Mapping(source = "updateDto.username", target = "username", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(source = "updateDto.firstName", target = "firstName", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(source = "updateDto.lastName", target = "lastName", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(source = "updateDto.email", target = "email", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(source = "updateDto.password", target = "password", nullValuePropertyMappingStrategy = IGNORE)
    @Mapping(source = "updateDto.phoneNumber", target = "phoneNumber", nullValuePropertyMappingStrategy = IGNORE)
    @Override
    User fromUpdateDto(UserUpdateDto updateDto, @MappingTarget User entity);
}
