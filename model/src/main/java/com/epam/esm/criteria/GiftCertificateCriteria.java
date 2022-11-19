package com.epam.esm.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

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
    String partOfName;
    String partOfDescription;
    String sortByName;
    String sortByCreateDate;
    List<String> tagNameList;
}
