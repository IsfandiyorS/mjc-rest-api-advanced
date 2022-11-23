package com.epam.esm.enums;

/**
 * Enum {@code ErrorCodes} presents values which will be thrown with exceptions
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
public enum ErrorCodes {

    OBJECT_ALREADY_EXIST("OBJECT_ALREADY_EXIST", "%s by this %s already exist"),
    OBJECT_NOT_FOUND_BY_FIELD("OBJECT_NOT_FOUND_BY_FIELD", "Object by this %s not found"),
    OBJECT_NOT_FOUND("OBJECT_NOT_FOUND", "%s not found"),
    OBJECT_NOT_FOUND_ID("OBJECT_NOT_FOUND_ID", "%s is not found with provided id"),
    OBJECT_ID_REQUIRED("OBJECT_ID_REQUIRED", "Object id not provided"),

    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "Method not allowed"),
    NO_HANDLER_FOUND("NO_HANDLER_FOUND", "No handler found"),
    INVALID_REQUEST_PARAMETER("INVALID_REQUEST_PARAMETER", "Invalid request parameters");

    public final String code;
    public final String message;

    ErrorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
