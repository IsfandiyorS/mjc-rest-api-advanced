package com.epam.esm.domain;

import com.epam.esm.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

/**
 * Abstract class {@code Auditable} represents to identify objects.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @CreatedDate
    @Column(nullable = false)
    LocalDateTime createDate = LocalDateTime.now();

    @CreatedBy
    String createdBy;

    @LastModifiedDate
    LocalDateTime lastUpdateDate;

    @LastModifiedBy
    String lastModifiedBy;

    @Column(columnDefinition = "NUMERIC default 0")
    State state = State.CREATED;

    public Auditable(Long id) {
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
