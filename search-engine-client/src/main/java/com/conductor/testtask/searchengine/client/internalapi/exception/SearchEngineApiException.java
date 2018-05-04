package com.conductor.testtask.searchengine.client.internalapi.exception;

public class SearchEngineApiException extends InternalApiException {
    public SearchEngineApiException() {
    }

    public SearchEngineApiException(String message) {
        super(message);
    }

    public SearchEngineApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
