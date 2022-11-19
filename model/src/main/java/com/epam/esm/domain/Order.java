package com.epam.esm.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.StringJoiner;

import static lombok.AccessLevel.PRIVATE;

@Entity(name = "Order")
@Table(name = "orders")
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class Order extends BaseAbstractDomain {

    BigDecimal price;

    Long orderQuantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @JoinColumn(name = "gift_certificate_id")
    GiftCertificate giftCertificate;

    public Order(Long id, BigDecimal price, Long orderQuantity, User user, GiftCertificate giftCertificate) {
        super(id);
        this.price = price;
        this.orderQuantity = orderQuantity;
        this.user = user;
        this.giftCertificate = giftCertificate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "price=" + price +
                ", orderQuantity=" + orderQuantity +
                ", user=" + user +
                ", giftCertificate=" + giftCertificate +
                '}';
    }
}
