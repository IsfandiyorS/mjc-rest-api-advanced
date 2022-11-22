package com.epam.esm.handler;

/**
 * Class {@code AlreadyExistException} used in the case of there is already created entity given by its field.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException() {
        super();
    }

    public AlreadyExistException(String message) {
        super(message);
    }

    public AlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistException(Throwable cause) {
        super(cause);
    }
}
