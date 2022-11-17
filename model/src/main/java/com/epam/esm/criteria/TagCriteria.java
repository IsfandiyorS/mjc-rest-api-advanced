package com.epam.esm.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

/**
 * Class {@code TagCriteria} indicates it will be used for filtering or sorting
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class TagCriteria implements Criteria{
    String tagName;
    String sortByTagName;
    String partOfTagName;
}
