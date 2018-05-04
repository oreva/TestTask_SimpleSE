package com.conductor.testtask.searchengine.server.internalapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/rest")
@PreAuthorize("hasRole('INTERNAL_API')")
public class SearchEngineApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchEngineApiController.class);


    @RequestMapping(value = "/put/document/{document}/key/{key}", method = RequestMethod.GET)
    public void putDocumentByKey(@PathVariable("document") String document,
                      @PathVariable("key") String key) {
        Objects.requireNonNull(document);
        Objects.requireNonNull(key);

        LOGGER.info("START putDocumentByKey for document '{}' and key '{}'", document, key);
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