package com.conductor.testtask.searchengine.client.service;

import com.conductor.testtask.searchengine.client.internalapi.InternalApi;
import com.conductor.testtask.searchengine.client.internalapi.SearchEngineApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

@Service
public class SearchEngineService {
    @Autowired
    private InternalApi internalApi;

    private SearchEngineApi getSearchEngineApi() {
        return internalApi.getSearchEngineApi();
    }

    public void putDocumentByKey(String document,
                                 String key) {
        Objects.requireNonNull(document);
        Objects.requireNonNull(key);

        getSearchEngineApi().putDocumentByKey(document, key);
    }

    public String getDocumentByKey(String key) {
        Objects.requireNonNull(key);

        return getSearchEngineApi().getDocumentByKey(key);
    }

    public Set<String> searchDocuments(String searchString) {
        return getSearchEngineApi().searchDocuments(searchString);
    }

    public void insertTestData() {
        getSearchEngineApi().insertTestData();
    }

    public HashMap<String, String> getAllData() {
        return getSearchEngineApi().getAllIndexedData();
    }
}
