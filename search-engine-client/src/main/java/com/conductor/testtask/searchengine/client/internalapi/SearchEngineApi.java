package com.conductor.testtask.searchengine.client.internalapi;

import com.conductor.testtask.searchengine.client.internalapi.exception.SearchEngineApiException;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import feign.codec.ErrorDecoder;

public interface SearchEngineApi {
    /*@RequestLine("POST /put/document/{document}/key/{key}")
    @Headers("Content-Type: application/x-www-form-urlencoded")*/
    @RequestLine("GET /put/document/{document}/key/{key}")
    void putDocumentByKey(@Param("document") String document,
                          @Param("key") String tenantId);

    class SearchEngineApiErrorDecoder implements ErrorDecoder {
        final ErrorDecoder delegate;

        SearchEngineApiErrorDecoder(ErrorDecoder delegate) {
            this.delegate = delegate;
        }

        @Override
        public Exception decode(String methodKey, Response response) {
            return new SearchEngineApiException("Error while performing request", delegate.decode(methodKey, response));
        }
    }
}
