package com.epam.esm.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

/**
 * Abstract class {@code GiftCertificate} represents gift certificate entity.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */

@Entity
@Data
@Builder
@Where(clause = "state <> 2")
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
            joinColumns = @JoinColumn(name = "gift_certificate_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    List<Tag> tagList;

    public GiftCertificate(Long id, String name, String description, BigDecimal price, Integer duration, List<Tag> tagList) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.tagList = tagList;
    }

    @Override
    public String toString() {
        return "GiftCertificate{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                ", tagList=" + tagList +
                '}';
    }
}
