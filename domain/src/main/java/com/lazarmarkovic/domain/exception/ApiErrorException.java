package com.lazarmarkovic.domain.exception;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public record ApiErrorException(
        HttpStatus status,
        Timestamp timestamp,
        String message
) {
}
