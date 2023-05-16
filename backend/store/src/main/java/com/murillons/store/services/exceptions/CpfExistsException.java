package com.murillons.store.services.exceptions;

public class CpfExistsException extends RuntimeException {
    public CpfExistsException(String message) {
        super(message);
    }
}
