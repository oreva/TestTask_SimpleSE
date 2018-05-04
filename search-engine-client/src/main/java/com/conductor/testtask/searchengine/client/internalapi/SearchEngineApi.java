package com.conductor.testtask.searchengine.client.internalapi;

import com.conductor.testtask.searchengine.client.internalapi.exception.SearchEngineApiException;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.util.HashMap;
import java.util.Set;

public interface SearchEngineApi {

    @RequestLine("GET /put/document/{document}/key/{key}")
    void putDocumentByKey(@Param("document") String document,
                          @Param("key") String key);

    @RequestLine("GET /get/document/key/{key}")
    String getDocumentByKey(@Param("key") String key);

    @RequestLine("GET /search/documents/{searchString}")
    Set<String> searchDocuments(@Param("searchString") String searchString);

    @RequestLine("GET /insertTestData")
    void insertTestData();

    @RequestLine("GET /getAll")
    HashMap<String, String> getAllIndexedData();

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
