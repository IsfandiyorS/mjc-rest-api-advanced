package com.epam.esm.dto.auth;

import com.epam.esm.dto.GenericCrudDto;
import com.epam.esm.enums.UserType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UserUpdateDto extends GenericCrudDto {

    @NotNull(message = "User id must be valid")
    Long id;

    @Size(min = 4, message = "Username length must be more than 4")
    String username;

    String firstName;

    String lastName;

    @Email(message = "Email should be valid")
    String email;

    @Size(min = 8, message = "Password length must be more than 8")
    String password;

    @Size(min = 12, message = "Phone number length must be over than 12. Such as: 998-XX-XXX-XX-XX")
    String phoneNumber;
}
