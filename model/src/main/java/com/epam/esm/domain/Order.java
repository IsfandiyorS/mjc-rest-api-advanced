package com.epam.esm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Entity(name = "Order")
@Table(name = "orders")
@Data
@Where(clause = "state <> 2")
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
