package com.conductor.testtask.searchengine.server.internalapi;

import com.conductor.testtask.searchengine.server.service.SearchEngineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(value = "/rest")
@PreAuthorize("hasRole('INTERNAL_API')")
public class SearchEngineApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchEngineApiController.class);

    @Autowired
    private SearchEngineService searchEngineService;

    @RequestMapping(value = "/put/document/{document}/key/{key}", method = RequestMethod.GET)
    public void putDocumentByKey(@PathVariable("document") String document,
                      @PathVariable("key") String key) {
        Objects.requireNonNull(document);
        Objects.requireNonNull(key);

        LOGGER.debug("START putDocumentByKey for document '{}' and key '{}'", document, key);

        searchEngineService.putDocumentByKey(document, key);

        LOGGER.debug("FINISH putDocumentByKey for document '{}' and key '{}'", document, key);
    }

    @RequestMapping(value = "/get/document/key/{key}", method = RequestMethod.GET)
    public String getDocumentByKey(@PathVariable("key") String key) {
        Objects.requireNonNull(key);

        LOGGER.debug("IN getDocumentByKey for key '{}'", key);

        return searchEngineService.getDocumentByKey(key);
    }

    @RequestMapping(value = "/search/documents/{searchString}", method = RequestMethod.GET)
    public Set<String> searchDocuments(@PathVariable("searchString") String searchString) {
        Objects.requireNonNull(searchString);

        LOGGER.info("IN searchDocuments for search string '{}'", searchString);

        return searchEngineService.searchKeysForAllOccurrences(searchString);
    }

    @RequestMapping(value = "/insertTestData", method = RequestMethod.GET)
    public void insertTestData() {
        searchEngineService.insertTestData();
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public HashMap<String, String> getAllData() {
        return searchEngineService.getAllData();
    }
}