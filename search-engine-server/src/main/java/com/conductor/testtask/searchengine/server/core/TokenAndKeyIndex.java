package com.conductor.testtask.searchengine.server.core;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TokenAndKeyIndex {
    private final Map<String, Set<String>> internalStorage = new HashMap<>();

    public void indexDocumentByKey(String document,
                                   String key) {
        Objects.requireNonNull(document);

        String[] tokens = splitDocumentToTokens(document);
        for (String token: tokens) {
            indexTokenByKey(token, key);
        }
    }

    private void indexTokenByKey(String token,
                                String key) {
        Set<String> keysByToken = getKeysByToken(token);
        if (null == keysByToken) {
            keysByToken = new HashSet<>();
        }

        keysByToken.add(key);
        internalStorage.put(token, keysByToken);
    }

    public Set<String> getKeysByToken(String token) {
        return internalStorage.get(token);
    }

    /**
     *
     * @param document
     * @return Set of keys for documents that contains ALL tokens from the input parameter 'document'
     */
    public Set<String> getKeysByDocumentForAndClause(String document) {
        Objects.requireNonNull(document);

        Set<String> resultSet = null;
        String[] tokens = splitDocumentToTokens(document);
        if (tokens.length > 0) {
            resultSet = getKeysByToken(tokens[0]);

            for (int i = 1; i < tokens.length; i++) {
                Set<String> keys = getKeysByToken(tokens[i]);
                if (null == keys) {
                    return null;
                }
                resultSet.retainAll(keys);
            }
        }
        return resultSet;
    }

    /**
     *
     * @param document
     * @return Set of keys for documents that contains ANY of the token from the input parameter 'document'
     */
    public Set<String> getKeysByDocumentForOrClause(String document) {
        Objects.requireNonNull(document);

        Set<String> resultSet = null;

        String[] tokens = splitDocumentToTokens(document);
        for (String token: tokens) {
            Set<String> keys = getKeysByToken(token);
            if (null != keys) {
                if (null == resultSet) {
                    resultSet = Sets.newHashSet();
                }
                resultSet.addAll(keys);
            }
        }
        return resultSet;
    }

    private String[] splitDocumentToTokens(String document) {
        return document.split("\\s+");
    }
}
