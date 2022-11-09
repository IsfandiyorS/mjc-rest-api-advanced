package com.epam.esm.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TagCriteria extends GenericCriteria{

    private String tagName;
    private String sortByTagName;
    private String partOfTagName;

}
