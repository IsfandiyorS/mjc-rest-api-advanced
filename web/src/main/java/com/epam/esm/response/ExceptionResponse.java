package com.epam.esm.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Class {@code ExceptionResponse} represents objects that will be returned as
 * a response when an error is generated.
 *
 * @author Sultonov Isfandiyor
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
    private int errorCode;
    private String errorMessage;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime time = LocalDateTime.now();

    public ExceptionResponse(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
