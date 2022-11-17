package com.epam.esm.dto.auth;

import com.epam.esm.dto.GenericDto;
import com.epam.esm.enums.UserType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

import static lombok.AccessLevel.PRIVATE;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UserDto extends RepresentationModel<UserDto> implements GenericDto {

    Long id;

    String username;

    String firstName;

    String lastName;

    String email;

    String phoneNumber;

    UserType userType;

    @Builder(builderMethodName = "userDtoBuilder")
    public UserDto(Long id, String username, String firstName, String lastName, String email, String phoneNumber, UserType userType) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
    }
}
