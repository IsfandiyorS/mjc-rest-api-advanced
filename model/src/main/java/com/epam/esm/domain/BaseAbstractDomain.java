package com.epam.esm.domain;

import com.epam.esm.enums.State;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

/**
 * Abstract class {@code AbstractDomain} represents to identify objects.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PROTECTED)
public abstract class BaseAbstractDomain {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    protected Long id;

    @CreatedDate
    @Column(nullable = false)
    protected LocalDateTime createDate = LocalDateTime.now();

    @LastModifiedDate
    protected LocalDateTime lastUpdateDate;

    @Column(columnDefinition = "NUMERIC default 0")
    protected State state = State.CREATED;

    public BaseAbstractDomain(Long id){
        this.id = id;
    }
}

