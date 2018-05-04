package com.conductor.testtask.searchengine.server.core;

import org.junit.Assert;
import org.junit.Test;

public class DocumentsByKeyStorageTest {

    private DocumentsByKeyStorage documentsByKeyStorage = new DocumentsByKeyStorage();

    @Test
    public void testPutAndGetDocumentByKey() throws Exception {
        String result = documentsByKeyStorage.getDocumentByKey("test1");

        Assert.assertNull(result);

        documentsByKeyStorage.putDocumentByKey("document", "test1");
        result = documentsByKeyStorage.getDocumentByKey("test1");

        Assert.assertEquals("document", result);
    }
}