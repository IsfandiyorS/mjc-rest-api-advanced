package com.epam.esm.domain;

import com.epam.esm.domain.audit.AppAuditorAware;
import com.epam.esm.domain.audit.Auditable;
import com.epam.esm.enums.State;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

import static javax.persistence.GenerationType.*;
import static lombok.AccessLevel.*;

/**
 * Abstract class {@code AbstractDomain} represents to identify objects.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@MappedSuperclass
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PROTECTED)
public abstract class BaseAbstractDomain extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = IDENTITY)
     Long id;

    @CreatedDate
    @Column(nullable = false)
     LocalDateTime createDate = LocalDateTime.now();

    @LastModifiedDate
     LocalDateTime lastUpdateDate;

    @Column(columnDefinition = "NUMERIC default 0")
     State state = State.CREATED;

    public BaseAbstractDomain(Long id){
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseAbstractDomain{" +
                "id=" + id +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", state=" + state +
                '}';
    }
}

