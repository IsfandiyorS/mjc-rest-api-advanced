package com.epam.esm.dto.auth;

import com.epam.esm.dto.GenericCrudDto;
import com.epam.esm.enums.UserType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UserUpdateDto extends GenericCrudDto {

    String username;

    String firstName;

    String lastName;

    String email;

    UserType userType;

}
