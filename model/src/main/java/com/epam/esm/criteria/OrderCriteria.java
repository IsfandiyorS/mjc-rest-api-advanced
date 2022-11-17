package com.epam.esm.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class OrderCriteria implements Criteria {
    String username;
    String giftCertificateName;
    String sortByMostExpensiveOrder;
    String sortByOrderedDate;
}
