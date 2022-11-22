package com.epam.esm.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.hateoas.RepresentationModel;

import static lombok.AccessLevel.*;

/**
 * Class {@code DataResponse} uses for cover all return responses as a one object
 * in {@link com.epam.esm.controller.GiftCertificateController} and
 * {@link com.epam.esm.controller.TagController} controllers
 *
 * @author Sultonov Isfandiyor
 * @since 1.0
 */
@Data
@FieldDefaults(level = PRIVATE)
public class DataResponse<T> {
    private T data;
    private boolean success;

    public DataResponse(T data) {
        this.data = data;
        this.success = true;
    }
    public DataResponse(boolean success){
        this.success = success;
    }
}
