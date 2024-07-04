package com.learning.spring.Exception;

public class ResourceFoundException extends Exception {
    private static final long serialVersionUID = 1L;
    public ResourceFoundException(String message) {
        super(message);
    }
}
