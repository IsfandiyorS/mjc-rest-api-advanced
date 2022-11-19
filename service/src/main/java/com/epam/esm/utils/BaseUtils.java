package com.epam.esm.utils;

import java.util.List;

/**
 * Class {@code BaseValidationUtils} used for basic Validation operations
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */

public class BaseUtils {

    public static boolean isEmptyString(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isEmptyList(List<?> items) {
        return items == null || items.isEmpty();
    }

    public static boolean isEmptyObject(Object l) {
        return l == null;
    }

}
