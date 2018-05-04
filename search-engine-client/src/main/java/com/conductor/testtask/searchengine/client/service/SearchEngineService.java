package com.conductor.testtask.searchengine.client.service;

import com.conductor.testtask.searchengine.client.internalapi.InternalApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
}
