package com.epam.esm.domain;

import com.epam.esm.enums.UserType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Entity(name = "User")
@Table(name = "users")
@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class User extends BaseAbstractDomain{

    @Column(unique = true, nullable = false)
    String username;

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @Column(unique = true, nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    @Column(unique = true, nullable = false)
    String phoneNumber;

    @Enumerated(EnumType.ORDINAL)
    UserType userType = UserType.USER;

    @OneToMany(mappedBy = "user")
    List<Order> orders;

}
