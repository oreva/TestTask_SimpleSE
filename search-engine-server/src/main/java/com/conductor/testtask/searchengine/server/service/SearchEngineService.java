package com.conductor.testtask.searchengine.server.service;

import com.conductor.testtask.searchengine.server.core.DocumentsByKeyStorage;
import com.conductor.testtask.searchengine.server.core.TokenAndKeyIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class SearchEngineService {

    @Autowired
    private DocumentsByKeyStorage documentsByKeyStorage;

    @Autowired
    private TokenAndKeyIndex tokenAndKeyIndex;

    public void putDocumentByKey(String document,
                                 String key) {
        Objects.requireNonNull(document);
        Objects.requireNonNull(key);

        documentsByKeyStorage.putDocumentByKey(document, key);
        tokenAndKeyIndex.indexDocumentByKey(document, key);
    }

    public String getDocumentByKey(String key) {
        Objects.requireNonNull(key);

        return documentsByKeyStorage.getDocumentByKey(key);
    }

    /**
     *
     * @param searchString
     * @return Result of search based on the following rule:
     * a document accepts search criteria if it contains ALL tokens from the searchString
     */
    public Set<String> searchKeysForAllOccurrences(String searchString) {
        return tokenAndKeyIndex.getKeysByDocumentForAndClause(searchString);
    }
}
