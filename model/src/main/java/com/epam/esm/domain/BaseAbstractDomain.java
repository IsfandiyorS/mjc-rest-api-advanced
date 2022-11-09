package com.epam.esm.domain;

import com.epam.esm.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

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
public abstract class BaseAbstractDomain {

    @Id
    @GeneratedValue
    protected Long id;

    @CreatedDate
    protected LocalDateTime createDate = LocalDateTime.now();

    @LastModifiedDate
    protected LocalDateTime lastUpdateDate;

    @Column(columnDefinition = "NUMERIC default 0")
    protected State state = State.CREATED;

    public BaseAbstractDomain(Long id){
        this.id = id;
    }
}

