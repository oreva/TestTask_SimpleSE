package com.conductor.testtask.searchengine.server.core;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class TokenAndKeyIndexTest {

    @Test
    public void getKeysByToken() throws Exception {
        TokenAndKeyIndex tokenAndKeyIndex = new TokenAndKeyIndex();
        Set<String> result = tokenAndKeyIndex.getKeysByToken("test1");

        Assert.assertNull(result);

        tokenAndKeyIndex.indexDocumentByKey("test1 document", "test1");
        result = tokenAndKeyIndex.getKeysByToken("document");

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("test1", result.toArray()[0]);
    }

    @Test
    public void getKeysByDocumentForAndClause() throws Exception {
        TokenAndKeyIndex tokenAndKeyIndex = new TokenAndKeyIndex();
        Set<String> result = tokenAndKeyIndex.getKeysByDocumentForAndClause("test1");

        Assert.assertNull(result);

        tokenAndKeyIndex.indexDocumentByKey("test1 document", "key1");
        tokenAndKeyIndex.indexDocumentByKey("test2 document", "key2");
        tokenAndKeyIndex.indexDocumentByKey("document", "key3");
        result = tokenAndKeyIndex.getKeysByDocumentForAndClause("test1 document");

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("key1", result.toArray()[0]);
    }

    @Test
    public void getKeysByDocumentForOrClause() throws Exception {
        TokenAndKeyIndex tokenAndKeyIndex = new TokenAndKeyIndex();
        Set<String> result = tokenAndKeyIndex.getKeysByDocumentForOrClause("test1");

        Assert.assertNull(result);

        tokenAndKeyIndex.indexDocumentByKey("test1 document", "key1");
        tokenAndKeyIndex.indexDocumentByKey("test2 document", "key2");
        tokenAndKeyIndex.indexDocumentByKey("document", "key3");
        result = tokenAndKeyIndex.getKeysByDocumentForOrClause("test1 document");

        Assert.assertNotNull(result);
        Assert.assertEquals(3, result.size());
        Assert.assertTrue(result.contains("key1"));
        Assert.assertTrue(result.contains("key2"));
        Assert.assertTrue(result.contains("key3"));
    }

}