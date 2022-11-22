package com.epam.esm.domain;

import com.epam.esm.domain.audit.Auditable;
import com.epam.esm.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

/**
 * Abstract class {@code AbstractDomain} represents to identify objects.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@MappedSuperclass
@Data
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

    public BaseAbstractDomain(Long id) {
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

