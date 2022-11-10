package com.epam.esm.dto.auth;

import com.epam.esm.dto.GenericDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class UserDto extends GenericDto {

    String username;

    String firstName;

    String lastName;

    String email;

    @Builder(builderMethodName = "userBuilder")
    public UserDto(Long id, String username, String firstName, String lastName, String email) {
        super(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
