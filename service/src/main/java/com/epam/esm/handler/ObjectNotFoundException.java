package com.epam.esm.handler;

/**
 * Class {@code ObjectNotFoundException} used in the case of there will not find object by given field.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException() {
    }

    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObjectNotFoundException(Throwable cause) {
        super(cause);
    }
}
