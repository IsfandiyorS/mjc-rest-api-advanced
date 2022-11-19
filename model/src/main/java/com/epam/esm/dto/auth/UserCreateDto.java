package com.epam.esm.dto.auth;

import com.epam.esm.dto.GenericCrudDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UserCreateDto extends GenericCrudDto {

    @NotBlank(message = "Username should be valid")
    @Size(min = 4, message = "Username length must be more than 4")
    String username;

    @NotBlank(message = "First name should be valid")
    String firstName;

    @NotBlank(message = "Last name should be valid")
    String lastName;

    @Email(message = "Email should be valid")
    String email;

    @NotBlank(message = "Password should be valid")
    @Size(min = 8, message = "Password length must be more than 8")
    String password;

    @NotBlank(message = "Phone number should be valid")
    @Size(min = 12, message = "Enter correct phone number. Such as: 998-XX-XXX-XX-XX")
    String phoneNumber;

}
