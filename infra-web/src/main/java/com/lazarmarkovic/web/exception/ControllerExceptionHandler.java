package com.lazarmarkovic.web.exception;

import com.lazarmarkovic.domain.exception.ApiErrorException;
import com.lazarmarkovic.domain.exception.EntityNotFoundException;
import com.lazarmarkovic.domain.exception.EntitySaveException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.Timestamp;
import java.time.Instant;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(EntityNotFoundException ex) {
        ApiErrorException apiError = new ApiErrorException(
            NOT_FOUND,
            Timestamp.from(Instant.now()),
            ex.getMessage()
        );
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(EntitySaveException.class)
    protected ResponseEntity<Object> handleEntitySaveException(EntitySaveException ex) {
        ApiErrorException apiError = new ApiErrorException(
                CONFLICT,
                Timestamp.from(Instant.now()),
                ex.getMessage()
        );
        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiErrorException apiError) {
        return new ResponseEntity<>(apiError, apiError.status());
    }
}
