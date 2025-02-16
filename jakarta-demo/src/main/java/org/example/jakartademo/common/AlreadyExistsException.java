package org.example.jakartademo.common;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String message, Object... args) {
        super(String.format(message, args));
    }
}
