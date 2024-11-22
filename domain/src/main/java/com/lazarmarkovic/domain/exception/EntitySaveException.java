package com.lazarmarkovic.domain.exception;

public class EntitySaveException extends RuntimeException {

    public EntitySaveException(String message) {
        super(message);
    }
}
