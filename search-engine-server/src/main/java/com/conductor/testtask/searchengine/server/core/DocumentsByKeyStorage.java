package com.conductor.testtask.searchengine.server.core;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DocumentsByKeyStorage {
    private final Map<String, String> internalStorage = new HashMap<>();

    public void putDocumentByKey(String document,
                                 String key) {
        internalStorage.put(key, document);
    }

    public String getDocumentByKey(String key) {
        return internalStorage.get(key);
    }
}
