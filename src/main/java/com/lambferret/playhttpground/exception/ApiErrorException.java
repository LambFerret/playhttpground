package com.lambferret.playhttpground.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiErrorException extends RuntimeException{
    private final HttpStatus status;
    private final String code;
    private final String message;

    public ApiErrorException(StatusCodes code) {
        this.status = code.status;
        this.code = code.name();
        this.message = code.description;
    }
}

