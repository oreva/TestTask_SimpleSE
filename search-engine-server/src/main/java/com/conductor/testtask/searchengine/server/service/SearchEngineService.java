package com.conductor.testtask.searchengine.server.service;

import com.conductor.testtask.searchengine.server.core.DocumentsByKeyStorage;
import com.conductor.testtask.searchengine.server.core.TokenAndKeyIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
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

    /**
     * 2 helper methods below to make usage of the app simpler
     */
    public void insertTestData() {
        putDocumentByKey("test document 1", "key1");
        putDocumentByKey("test document 2", "key2");
        putDocumentByKey("hello world", "hello");
        putDocumentByKey("document", "key3");
        putDocumentByKey("some simple document 1 2 12", "key4");
        putDocumentByKey("conductor test program simple search engine", "key5");
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public HashMap<String, String> getAllData() {
        return documentsByKeyStorage.getAll();
    }
}
