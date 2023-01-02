package com.lambferret.playhttpground.exception;

import org.springframework.http.HttpStatus;

public enum StatusCodes {
    P001(HttpStatus.OK, "정상 처리"),
    P002(HttpStatus.CREATED, "정상 생성"),
    P003(HttpStatus.ACCEPTED, "정상 요청"),
    F001(HttpStatus.BAD_REQUEST, "최대 5개의 CUD요청만 가능합니다."),
    F002(HttpStatus.NOT_FOUND, "수정 대상을 찾을 수 없습니다."),
    F003(HttpStatus.NOT_FOUND, "TEST 3"),
    F004(HttpStatus.NOT_FOUND, "TEST 4 : RAISED EXCEPTION"),



    ;

    public final HttpStatus status;
    public final String description;
    StatusCodes(HttpStatus status, String description) {
        this.status = status;
        this.description = description;
    }
}
