package com.conductor.testtask.searchengine.client.internalapi;

import com.conductor.testtask.searchengine.client.internalapi.exception.SearchEngineApiException;
import feign.RequestLine;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.util.Set;

/**
 * Created by Olga Reva on 10/24/2017.
 */
public interface SearchEngineApi {
    @RequestLine("GET /tenant/list/active/ids")
    Set<String> findAllActiveTenantIds();

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
