package com.epam.esm.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import static lombok.AccessLevel.*;

/**
 * Abstract class {@code GiftCertificate} represents gift certificate entity.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class GiftCertificate extends BaseAbstractDomain {

    String name;

    String description;

    BigDecimal price;

    Integer duration;

    @OneToMany(mappedBy = "giftCertificate")
    List<Order> orders;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "gift_certificate_tag",
            joinColumns = @JoinColumn(name = "gift_certificate_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    List<Tag> tagList;
}
