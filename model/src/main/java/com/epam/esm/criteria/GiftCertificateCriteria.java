package com.epam.esm.criteria;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.*;

/**
 * Class {@code GiftCertificateCriteria} indicates it will be used for filtering or sorting
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class GiftCertificateCriteria implements Criteria {
    String name;
    List<String> tagNameList;
    String partOfName;
    String partOfDescription;
    String sortByName;
    String sortByCreateDate;
}
