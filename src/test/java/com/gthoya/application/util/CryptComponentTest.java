package com.gthoya.application.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CryptComponentTest {
    private CryptComponent cryptComponent;

    @Before
    public void before() throws Exception {
        cryptComponent = new CryptComponent();
    }

    @Test
    public void testEncrypt() throws Exception {
        System.out.println(cryptComponent.encrypt("test"));
    }

    @Test
    public void testDecrypt() throws Exception {
        Assert.assertEquals(cryptComponent.decrypt("JzSnOy5oaMA1+hPTU6pU6A=="), "test");
    }
}
