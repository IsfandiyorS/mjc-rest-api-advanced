package com.epam.esm.response;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

/**
 * Class {@code DataResponse} uses for cover all return responses as a one object
 * in {@link com.epam.esm.controller.GiftCertificateController} and
 * {@link com.epam.esm.controller.TagController} controllers
 *
 * @author Sultonov Isfandiyor
 * @since 1.0
 */
@Data
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
