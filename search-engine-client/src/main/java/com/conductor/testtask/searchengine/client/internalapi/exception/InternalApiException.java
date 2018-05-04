package com.conductor.testtask.searchengine.client.internalapi.exception;

public class InternalApiException extends RuntimeException {
    public InternalApiException() {
    }

    public InternalApiException(String message) {
        super(message);
    }

    public InternalApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
