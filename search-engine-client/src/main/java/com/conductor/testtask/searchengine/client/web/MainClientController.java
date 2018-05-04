package com.conductor.testtask.searchengine.client.web;

import com.conductor.testtask.searchengine.client.service.SearchEngineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(value = "/api")
@PreAuthorize("hasRole('INTERNAL_API')")
public class MainClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainClientController.class);

    @Autowired
    private SearchEngineService searchEngineService;

    @RequestMapping(method = RequestMethod.GET, value = "/put/document/{document}/key/{key}")
    public void putDocumentByKey(@PathVariable("document") String document,
                                 @PathVariable("key") String key) {
        Objects.requireNonNull(document);
        Objects.requireNonNull(key);

        LOGGER.debug("START indexing document '{}' by key '{}'", document, key);

        searchEngineService.putDocumentByKey(document, key);

        LOGGER.debug("FINISH indexing document '{}' by key '{}'", document, key);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/document/key/{key}")
    public String getDocumentByKey(@PathVariable("key") String key) {
        Objects.requireNonNull(key);

        String result = searchEngineService.getDocumentByKey(key);

        LOGGER.debug("IN getDocumentByKey for key '{}'. Document = '{}'.", key, result);

        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{searchString}")
    public Set<String> searchDocuments(@PathVariable("searchString") String searchString) {
        Objects.requireNonNull(searchString);

        LOGGER.debug("IN searchDocuments for string '{}'", searchString);

        return searchEngineService.searchDocuments(searchString);
    }
}