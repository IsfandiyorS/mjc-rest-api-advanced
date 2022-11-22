package com.epam.esm.domain;

import com.epam.esm.enums.UserType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.StringJoiner;

import static lombok.AccessLevel.PRIVATE;

@Entity(name = "User")
@Table(name = "users")
@Data
@Builder
@Where(clause = "state <> 2")
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

    @Column(nullable = false)
    String email;

    @Column(nullable = false)
    String password;

    @Column(nullable = false)
    String phoneNumber;

    @Column(columnDefinition = "NUMERIC default 0", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    UserType userType = UserType.USER;

    @OneToMany(mappedBy = "user")
    List<Order> orders;

    public User(Long id, String username, String firstName, String lastName, String email, String password, String phoneNumber, UserType userType, List<Order> orders) {
        super(id);
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userType=" + userType +
                '}';
    }
}
