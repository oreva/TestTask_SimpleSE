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

import java.util.List;
import java.util.Objects;

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

        LOGGER.info("START indexing document '{}' by key '{}'", document, key);

        searchEngineService.putDocumentByKey(document, key);

        LOGGER.info("FINISH indexing document '{}' by key '{}'", document, key);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/get/document/key/{key}")
    public String getDocumentByKey(@PathVariable("key") String key) {
        Objects.requireNonNull(key);

        String result = searchEngineService.getDocumentByKey(key);

        LOGGER.info("IN getDocumentByKey for key '{}'. Document = '{}'.", key, result);

        return result;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/search/{searchString}")
    public List<String> searchDocuments(@PathVariable("searchString") String searchString) {
        Objects.requireNonNull(searchString);

        LOGGER.info("IN searchDocuments for string '{}'", searchString);

        List<String> result = searchEngineService.searchDocuments(searchString);

        return result;
    }

    /*@RequestMapping(method = GET, value = "/accounts/list/{tenantId}")
    public AutotaskAccountListData listAccounts(@PathVariable("tenantId") @Nonnull String tenantId,
                                                @RequestParam("searchString") String searchString,
                                                @RequestParam("page") Integer page,
                                                @RequestParam("reload") boolean reload) {
        Objects.requireNonNull(tenantId);
        Objects.requireNonNull(searchString);

        DecryptedTenant tenant = tenantService.findDecryptedAutotaskTenantById(tenantId);
        if (null != tenant) {
            Credentials credentials = Credentials.from(tenant);
            if (reload) {
                autotaskApiGateway.clearAccountsCache(credentials);
            }
            return autotaskApiGateway.searchAccounts(credentials, searchString, page, AUTOTASK_ACCOUNTS_UI_PAGE_SIZE);
        } else {
            throw new AutotaskNoCredentialsException();
        }
    }*/



    /*@RequestMapping(method = POST, value = "/atBillingMapping/saveAll")
    public ValidationResponseData saveAllMappings(
            @RequestHeader("X-USER-TENANT") String tenantId,
            @RequestBody @Nonnull ATBillingMappingsListData data) {
        Objects.requireNonNull(tenantId);
        Objects.requireNonNull(data);

        ValidationResponseData result = new ValidationResponseData()
                .withValidationStatus(
                        autotaskBillingService.saveAllMappings(tenantId, data.getMappings())
                );
        return result;
    }*/
}