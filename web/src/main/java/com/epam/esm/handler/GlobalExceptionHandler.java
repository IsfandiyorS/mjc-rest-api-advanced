package com.epam.esm.handler;

import com.epam.esm.exceptions.AlreadyExistException;
import com.epam.esm.exceptions.ObjectNotFoundException;
import com.epam.esm.enums.ErrorCodes;
import com.epam.esm.response.ExceptionResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

import static com.epam.esm.enums.ErrorCodes.*;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * Class {@code GlobalExceptionHandler} presents entity which will be returned from controller
 * in case generating exceptions
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<Object> handleNoHandlerFoundException() {
        return new ResponseEntity<>(
                new ExceptionResponse(NOT_FOUND.value(), NO_HANDLER_FOUND.message),
                NOT_FOUND
        );
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(value = ObjectNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(
            final RuntimeException ex, final WebRequest request) {
        return constructExceptionResponse(ex, request, NOT_FOUND);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(value = {AlreadyExistException.class, JsonProcessingException.class})
    public ResponseEntity<ExceptionResponse> handleBadRequestException(
            final RuntimeException ex, final WebRequest request) {
        return constructExceptionResponse(ex, request, BAD_REQUEST);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public final ResponseEntity<Object> methodNotAllowedExceptionException() {
        ExceptionResponse errorResponse = new ExceptionResponse(HttpStatus.METHOD_NOT_ALLOWED.value(), METHOD_NOT_ALLOWED.message);
        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public final ResponseEntity<Object> handleBadRequestExceptions() {
        ExceptionResponse errorResponse = new ExceptionResponse(BAD_REQUEST.value(), INVALID_REQUEST_PARAMETER.message);
        return new ResponseEntity<>(errorResponse, BAD_REQUEST);
    }


    private ResponseEntity<ExceptionResponse> constructExceptionResponse(
            final Exception ex, final WebRequest request, HttpStatus status) {
        return new ResponseEntity<>(
                new ExceptionResponse(status.value(), ex.getMessage()),
                status
        );
    }
}