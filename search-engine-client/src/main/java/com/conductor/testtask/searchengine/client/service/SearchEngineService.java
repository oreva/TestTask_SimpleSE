package com.conductor.testtask.searchengine.client.service;

import com.conductor.testtask.searchengine.client.internalapi.InternalApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class SearchEngineService {
    @Autowired
    private InternalApi internalApi;

    public void putDocumentByKey(String document,
                                 String key) {
        Objects.requireNonNull(document);
        Objects.requireNonNull(key);

        internalApi.getSearchEngineApi().putDocumentByKey(document, key);
    }

    public String getDocumentByKey(String key) {
        Objects.requireNonNull(key);

        return internalApi.getSearchEngineApi().getDocumentByKey(key);
    }

    public Set<String> searchDocuments(String searchString) {
        return internalApi.getSearchEngineApi().searchDocuments(searchString);
    }
}
