package com.epam.esm.domain.audit;

import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PROTECTED;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = PROTECTED)
public abstract class Auditable<T> {

    @CreatedBy
    T insertedBy;

    @CreatedDate
    LocalDateTime insertedDate;

    @LastModifiedBy
    T lastModifiedBy;

    @LastModifiedDate
    LocalDateTime lastModifiedDate;
}
