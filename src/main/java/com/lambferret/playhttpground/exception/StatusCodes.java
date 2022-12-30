package com.lambferret.playhttpground.exception;

import org.springframework.http.HttpStatus;

public enum StatusCodes {
    P001(HttpStatus.OK, "정상 처리"),
    P002(HttpStatus.CREATED, "정상 생성"),
    P003(HttpStatus.ACCEPTED, "정상 요청"),
    F001(HttpStatus.NOT_FOUND, "TEST 1"),
    F002(HttpStatus.NOT_FOUND, "TEST 2"),
    F003(HttpStatus.NOT_FOUND, "정상 요청"),
    F004(HttpStatus.NOT_FOUND, "정상 요청"),



    ;

    public final HttpStatus status;
    public final String description;
    StatusCodes(HttpStatus status, String description) {
        this.status = status;
        this.description = description;
    }
}
