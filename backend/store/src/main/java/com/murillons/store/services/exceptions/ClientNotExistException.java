package com.murillons.store.services.exceptions;

public class ClientNotExistException extends RuntimeException {
    public ClientNotExistException(String message) {
        super(message);
    }
}
